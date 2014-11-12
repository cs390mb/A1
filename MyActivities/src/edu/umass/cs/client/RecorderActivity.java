package edu.umass.cs.client;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import edu.umass.cs.client.widget.ContextImageWidget;
import edu.umass.cs.voice.MicrophoneRecorder;
import edu.umass.cs.voice.MicrophoneRecorder.MicrophoneListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * 
 * 
 * @author musthag
 * 
 */
public class RecorderActivity extends Activity implements MicrophoneListener{
    private final static String TAG = "RecorderActivity";
    private final int CAPACITY = MicrophoneRecorder.frequency*60*15; // split files by 15 minute each
	private static String filename = null;
	private static DataOutputStream dos = null;
	private static int totalWritten = 0;
	
	
	private RecorderActivity activity;
	private Button recordButton;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	activity = this;
    	super.onCreate(savedInstanceState);
    
        setContentView(R.layout.record);    
        
        recordButton = (Button) findViewById(R.id.RecordButton);
        
        recordButton.setText(MicrophoneRecorder.getInstance().isRecording() ? R.string.stop : R.string.record);
        recordButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MicrophoneRecorder recorder = MicrophoneRecorder.getInstance();
				if(recorder.isRecording()){
					Log.d(TAG,"stopping recording");
					recorder.stopRecording();
					recordButton.setText(R.string.record);
					closeOutAudioFile();
				} else {
					Log.d(TAG,"starting recording");
					recorder.registerListener(activity);
					recorder.startRecording();
					recordButton.setText(R.string.stop);
				}
			}
		});
    }
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
    
	protected String generateFileName() {
		return "MICR_" + Calendar.getInstance().getTimeInMillis();
	}
	
	
	private File getStorageLocation(){
		File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		return root;
	}
    private void setupNewAudioFile(){
		try {
			filename = generateFileName() + ".pcm";
			File audioFile = new File(getStorageLocation(),filename + ".inprogress");
	
			Log.d(TAG,"created new audioFile: " +audioFile.getAbsolutePath());
			OutputStream os = new FileOutputStream(audioFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			dos = new DataOutputStream(bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void closeOutAudioFile(){
		try{ 
			dos.close(); 
		} catch (Exception e){
			e.printStackTrace();
		}						
		File audioFile = new File(getStorageLocation(), filename + ".inprogress");
		audioFile.renameTo(new File(getStorageLocation(), filename)); 				// mark file as ready to upload 
		filename = null;										// reset filename so new file is created
		totalWritten = 0;										// reset totalWritten
		Log.d(TAG,"closed out file: " + audioFile.getAbsolutePath());
	}
	@Override
	public void microphoneBuffer(short[] buffer, int window_size) {
		Log.d(TAG,"received buffer");
		try{
			if (filename == null){ 					// this is a new file, create it 
				setupNewAudioFile();
			}
			for (int i = 0; i < window_size; i++){ 	// write to file
				dos.writeShort(buffer[i]);
			}
			totalWritten += window_size; 			// increment total written
			if (totalWritten >= CAPACITY){			// Did we reach the capacity for current file?
				closeOutAudioFile();  
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}