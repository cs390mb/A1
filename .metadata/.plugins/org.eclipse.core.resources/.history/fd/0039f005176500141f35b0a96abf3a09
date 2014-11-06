/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.umass.cs.client;

import java.util.LinkedList;
import java.util.List;

import edu.umass.cs.client.widget.ContinuousContextImageWidget;
import edu.umass.cs.client.widget.ContextImageWidget;
import edu.umass.cs.client.widget.WidgetBase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContextActivity extends ListActivity {
	
	public static enum STREAMS {ACTIVITY
//									,VOICE
									,ACCX
									,ACCY
									,ACCZ
								};
	private WidgetBase[] widgets = new WidgetBase[STREAMS.values().length];
	
	private static final String TAG = "ContextActivity";
    
	
	// Datasource for the listview
    private ContextAdapter adapter;
    
	// Messenger service for exchanging messages with the background service
	private Messenger mService = null;
    
    // Variable indicating if this activity is connected to the service
	private boolean mIsBound;
	//   Messenger receiving messages from the background service to update UI
	private final Messenger mMessenger = new Messenger(new IncomingHandler());

    
    // Connection with the service
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
        	Log.d(TAG,"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        	Log.d(TAG,"Service connected");
            mService = new Messenger(service);
            mIsBound = true;
        	sendMessageToService(Context_Service.MSG_REGISTER_CLIENT);
       		Log.d(TAG,"after registering client");

        }

        public void onServiceDisconnected(ComponentName className) {
        	Log.d(TAG,"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        	Log.d(TAG,"Service disconnected");
            // This is called when the connection with the service has been unexpectedly disconnected - process crashed.
        	mIsBound = false;
            mService = null;
        }
    };

    
    @SuppressLint("HandlerLeak")
	class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
        	if (widgets ==null) return;
            switch (msg.what) {
	            case Context_Service.MSG_ACTIVITY_STATUS:
	            {
	            	Log.d(TAG,"got message");
	            	String activity = msg.getData().getString("activity");
	            	Log.d(TAG,"activity:" + activity);
	            	int state = getStateFromActivityString(activity);
	            	if (widgets[STREAMS.ACTIVITY.ordinal()] !=null){
	            		((ContextImageWidget)widgets[STREAMS.ACTIVITY.ordinal()]).history_view.add(state);
	            		((ContextImageWidget)widgets[STREAMS.ACTIVITY.ordinal()]).setImage(state);
	            	}
	            	break;
	            }
	            case Context_Service.MSG_ACCEL_VALUES:
	            {
	            	float accX = msg.getData().getFloat("accx");
	            	float accY = msg.getData().getFloat("accy");
	            	float accZ = msg.getData().getFloat("accz");
	            	if (widgets[STREAMS.ACCX.ordinal()] !=null){
	            		((ContextImageWidget)widgets[STREAMS.ACCX.ordinal()]).history_view.add(accX);
	            	}
	            	if (widgets[STREAMS.ACCY.ordinal()] !=null){
	            		((ContextImageWidget)widgets[STREAMS.ACCY.ordinal()]).history_view.add(accY);
	            	}
	            	if (widgets[STREAMS.ACCZ.ordinal()] !=null){
	            		((ContextImageWidget)widgets[STREAMS.ACCZ.ordinal()]).history_view.add(accZ);
	            	}
	            	break;
	            }
	            default:
	                super.handleMessage(msg);
            }
        }
    }
    
    /**
     * Binds this activity to the service if the service is already running
     */
    private void bindToServiceIfIsRunning() {
        //If the service is running when the activity starts, we want to automatically bind to it.
        if (Context_Service.isRunning()) {
        	Log.d(TAG,"binding to service");
        	bindService(new Intent(this, Context_Service.class), mConnection, Context.BIND_AUTO_CREATE);
        }
    }
	
	
    /**
     * This method is required to send a request to the background service.
     * In current application, we are not sending any message yet.
     * @param message
     */
    private void sendMessageToService(int message) {
        if (mIsBound) {
            if (mService != null) {
            	try {
                    Message msg = Message.obtain(null, message);
                    msg.replyTo = mMessenger;
                    mService.send(msg);
                } catch (RemoteException e) {
                }
            }
        }
    }
    
    private int getStateFromActivityString(String label){
    	if(label.equals("STATIONARY"))
    		return 0;
    	else if(label.equals("WALKING"))
    		return 1;
    	else if(label.equals("DRIVE"))
    		return 2;
    	return -1;
    }
    
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(null);
        
//        View view = this.getWindow().getDecorView();
//        view.setBackgroundColor(Color.WHITE);
    }
    
    
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
        //Bind to the service if it is already running
        bindToServiceIfIsRunning();
        if(Context_Service.selected.size() > 0){
        	widgets = new WidgetBase[STREAMS.values().length];
        	if (adapter ==null){
        		adapter = new ContextAdapter();
        		setListAdapter(adapter);
        	}
            drawWidgets();
        } 
    }
    
    private void drawWidgets(){
    	List<Integer> selected = Context_Service.selected;
    	for(int i : selected){
    		Log.d(TAG,"selected: " + i);
    		switch(STREAMS.values()[i]){
    			case ACTIVITY:
    			    if (Context_Service.raw_activity_history == null) 
    			   		Context_Service.raw_activity_history = new LinkedList<Integer>();
    			   	widgets[i] = new ContextImageWidget(this,2,Context_Service.raw_activity_history);
    			   	widgets[i].setTitle("Raw Activity: ");
    			   	widgets[i].addOrRemoveTitleViewAsNecessary();
    				break;
    			case ACCX:
    			    if (Context_Service.accx_history == null) 
    			   		Context_Service.accx_history = new LinkedList<Float>();
    			   	widgets[i] = new ContinuousContextImageWidget(this,-20,20,150,Context_Service.accx_history);
    			   	widgets[i].setTitle(STREAMS.ACCX.toString());
    			   	widgets[i].addOrRemoveTitleViewAsNecessary();
    				break;
    			case ACCY:
    			    if (Context_Service.accy_history == null) 
    			   		Context_Service.accy_history = new LinkedList<Float>();
    			   	widgets[i] = new ContinuousContextImageWidget(this,-20,20,150,Context_Service.accy_history);
    			   	widgets[i].setTitle(STREAMS.ACCY.toString());
    			   	widgets[i].addOrRemoveTitleViewAsNecessary();
    				break;
    			case ACCZ:
    			    if (Context_Service.accz_history == null) 
    			   		Context_Service.accz_history = new LinkedList<Float>();
    			   	widgets[i] = new ContinuousContextImageWidget(this,-20,20,150,Context_Service.accz_history);
    			   	widgets[i].setTitle(STREAMS.ACCZ.toString());
    			   	widgets[i].addOrRemoveTitleViewAsNecessary();
    				break;
    		}
    	}
    }

    @Override
    public void onPause() {
    	if (mIsBound) {
        	sendMessageToService(Context_Service.MSG_UNREGISTER_CLIENT);
            // Detach our existing connection.
            unbindService(mConnection);
        }
    	setListAdapter(null);
    	adapter = null;
    	widgets=null;
        super.onPause();
    }    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_pick:
            	Intent intent = new Intent(getApplicationContext(),PickerActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private class ContextAdapter extends BaseAdapter {

    	/****/
        @Override
        public int getViewTypeCount(){
        	return Context_Service.selected.size();
        }
 
        @Override
        public int getItemViewType(int position) {
            return position;
        }
        
        @Override
        public int getCount() {
            return Context_Service.selected.size();
        }
 
        @Override
        public String getItem(int position) {
            return "";
        }
 
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        private int getWidgetIndexFromPosition(int position){
        	int index = -1, real = -1;
        	while(real < position){
        		if(widgets[++index] != null){
        			real++;
        		}
        	}
        	return index;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.v(TAG,"getView " + position + " " + convertView);
            if (convertView == null) {
                convertView = newView(position, getActivity());
            } 
            //todo:: insert code to update values of the convertView to fit the data 
            // at position
            return convertView;
        }
        
        public View newView(int position, Context context) {
    		return widgets[getWidgetIndexFromPosition(position)];
        }
    }
    private Activity getActivity(){
    	return this;
    }
}
