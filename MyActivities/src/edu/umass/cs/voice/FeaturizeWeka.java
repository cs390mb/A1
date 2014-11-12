package edu.umass.cs.voice;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.LinkedList;



public class FeaturizeWeka {
    private static String PATH = "??"; //TODO:Add the path where you want the file to 
	private static String FILE = "training"; 
//	private static String SCRATCH_PATH = "/Users/musthag/research/mcrowd/scratch/";
//	private static final int MAX_WIN = 128;
//	private static final int AD_WIN = 64;
	private static final int BITRATE = 8000;
	//private static final int INFERENCE_LENGTH = BITRATE*2;
	//private static final int FRAME_SIZE = 512;
	private static final int FRAME_SIZE_MS = 25; // each frame is 25ms
    private static final int SAMPLES_PER_FRAME = FRAME_SIZE_MS*8; //25ms = 8*25 = 200 samples
    
	private static final int FFT_SIZE = 8192;
    private static final int MFCCS_VALUE = 12;
    private static final int MEL_BANDS = 20;
	
	
	private static FFT featureFFT;
    private static Window featureWin;
    private static MFCC featureMFCC;
	
	//public static void ComputeFeaturesForFrame(short[] data16bit, int size, int voice)
    public static void ComputeFeaturesForFrame(short[] data16bit, int size, int voice, int index){
    
    
		double fftBufferR[] = new double[FFT_SIZE];
        double fftBufferI[] = new double[FFT_SIZE];
        double featureCepstrum[] = new double[MFCCS_VALUE];

        // Frequency analysis
        Arrays.fill(fftBufferR, 0);
        Arrays.fill(fftBufferI, 0);

        // Convert audio buffer to doubles
        for (int i = 0; i < size; i++)
        {
                fftBufferR[i] = data16bit[i];
        }

        // In-place windowing
        featureWin.applyWindow(fftBufferR);

        // In-place FFT
        featureFFT.fft(fftBufferR, fftBufferI);

        // Get MFCCs
        featureCepstrum = featureMFCC.cepstrum(fftBufferR, fftBufferI);
        System.out.print(featureCepstrum[0]+"");
    	for(int i=1;i<MFCCS_VALUE;i++){
    		System.out.print("," + featureCepstrum[i]);
    	}
//    	System.out.printf(",%s",voice==1 ? "true" : "false");
//    	System.out.printf("\n");
    	System.out.println(voice==1 ? ",true" : ",false");
	}
	
	public static int processFile(File inputFile,Pair[] voicelabels){
        //byte buf[] = new byte[INFERENCE_LENGTH*2];
        byte buf[] = new byte[BITRATE*2];

        FileInputStream fis;
		try {
			fis = new FileInputStream(inputFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return 0;
		}
        BufferedInputStream bis = new BufferedInputStream(fis);
        
        int bytesRead = 0;
        int pointer = 0;
        int frame_count =0;
    	try {
	        while((bytesRead = bis.read(buf,0,BITRATE*2)) == BITRATE*2) {
	        	//ShortBuffer sb = ByteBuffer.wrap(buf).asShortBuffer();
	            short[] sArray = new short[BITRATE];
	            ByteBuffer.wrap(buf).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sArray);
	            int voice = 0;
	            if (pointer < voicelabels.length && frame_count >= voicelabels[pointer].start){
	            	voice = 1;
	            	if (frame_count == voicelabels[pointer].end){
	            		pointer++;
	            	}
	            }
	            //ComputeFeaturesForFrame(sArray,BITRATE,voice);
	            for(int k=0;k<BITRATE;k+=SAMPLES_PER_FRAME){
	    		    ComputeFeaturesForFrame(sArray,SAMPLES_PER_FRAME,voice,k);
	    		}
	            frame_count++;
	        }

			bis.close();
			fis.close();
			return frame_count;
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	return frame_count;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		File file=new File(PATH+"training.arff");
		FileOutputStream fis = new FileOutputStream(file);  
		PrintStream out = new PrintStream(fis);  
		System.setOut(out); 
		//intiialize 
		featureFFT = new FFT(FFT_SIZE);
        featureWin = new Window(BITRATE);
        featureMFCC = new MFCC(FFT_SIZE, MFCCS_VALUE, MEL_BANDS, BITRATE);
		
        int voiced =0;
        int un_voiced = 0;
		
        String filename = FILE + ".raw";
		
        printARFFHeader();
        
        Pair[] voiceLabels = getVoiceLabels(FILE + ".label.txt");
        if (voiceLabels.length ==0) System.exit(1); 
        
        processFile(new File(PATH + filename), voiceLabels);	
		
		
		//System.out.printf("voiced:%d nonvoiced:%d\n",voiced,un_voiced);
	}
	
	public static Pair[] getVoiceLabels(String filename){
		LinkedList<Pair> result = new LinkedList<Pair>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(PATH + filename));
	        String line;
	        while((line = br.readLine()) != null) {
	        	//System.out.println(line);
	             Pair p = new Pair();
	             String[] splits = line.split("-");
	             String[] time = splits[0].split(":");
	             p.start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
	             time = splits[1].split(":");
	             p.end = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
	             result.add(p);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		Pair[] aResult = new Pair[result.size()];
//		for(Pair p : result){
//			System.out.printf("%d - %d\n",p.start,p.end);
//		}
		return result.toArray(aResult);
	}
	
	public static class Pair{
		public int start =0;
		public int end = 0;
	}
	
	public static void printARFFHeader(){
		System.out.println("@relation speech\n");
        System.out.println("@attribute mfcc1 numeric");
        System.out.println("@attribute mfcc2 numeric");
        System.out.println("@attribute mfcc3 numeric");
        System.out.println("@attribute mfcc4 numeric");
        System.out.println("@attribute mfcc5 numeric");
        System.out.println("@attribute mfcc6 numeric");
        System.out.println("@attribute mfcc7 numeric");
        System.out.println("@attribute mfcc8 numeric");
        System.out.println("@attribute mfcc9 numeric");
        System.out.println("@attribute mfcc10 numeric");
        System.out.println("@attribute mfcc11 numeric");
        System.out.println("@attribute mfcc12 numeric");
        System.out.println("@attribute speech {true,false}");
        System.out.println("@data\n");
	}

}
