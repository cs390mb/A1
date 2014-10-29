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

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PickerActivity extends ListActivity {
	
	private static final String TAG = "PickerActivity";
    
    ContextActivity.STREAMS[] values = ContextActivity.STREAMS.values();
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      
        ArrayAdapter<ContextActivity.STREAMS> adapter = new ArrayAdapter<ContextActivity.STREAMS>(this,
                      android.R.layout.simple_list_item_multiple_choice, values);
        
        setListAdapter(adapter); 
        final ListView lv = getListView();
        
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				SparseBooleanArray a = lv.getCheckedItemPositions();
                ArrayList<Integer> selected = new ArrayList<Integer>();
                for(int i = 0; i < values.length ; i++){
                    if (a.get(i)){
                    	Log.d(TAG,"stored: " + i);
                    	selected.add(i);
                    }
                }
                Context_Service.selected = selected; 
			}
			
        	
        });                
        for(int i : Context_Service.selected){
        	lv.setItemChecked(i, true);	
        }
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
        
    }

    @Override
    public void onPause() {
        super.onPause();
    }    
    
}
