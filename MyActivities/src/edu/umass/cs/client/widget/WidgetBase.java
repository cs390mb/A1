package edu.umass.cs.client.widget;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;


/**
 * WidgetBase.java
 * @author musthag
 */
public abstract class WidgetBase extends RelativeLayout
{
	private static final String LOG_TAG = "WidgetBase";
	
	// component ids, so that they can be referenced in LayoutParams
	protected static final int TITLE_ID =10;
	protected static final int DESCRIPTION_ID=20;
	protected static final int BODY_ID = 30;
	
        
    protected Context context;
    protected int field_id = -1;
    protected String title = null;
    protected String description = null;
    
    protected TextView title_view;
    protected TextView description_view;
    
    protected RelativeLayout body;
    protected RelativeLayout.LayoutParams bodyParams;
    
    protected DataHandler data_handler;
    
    public WidgetBase(Context context, int field_id, String title, String description) {
    	super(context);
    	this.context = context;
    	setFieldID(field_id);
    	setTitle(title);
    	setDescription(description);
    	
    	body = new RelativeLayout(context);
		body.setId(BODY_ID);
		bodyParams =new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
    	bodyParams.addRule(RelativeLayout.BELOW,TITLE_ID);
    	bodyParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
    	float scale = context.getResources().getDisplayMetrics().density;
    	int padding = Math.round(10*scale);
    	body.setPadding(padding, padding, padding, padding);
		this.addView(body,bodyParams); // add to view
    }
    public WidgetBase(Context context) {
    	super(context);
    	this.context = context;
    	
    	body = new RelativeLayout(context);
		body.setId(BODY_ID);
		bodyParams =new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		bodyParams.addRule(RelativeLayout.BELOW,TITLE_ID);
		bodyParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
    	float scale = context.getResources().getDisplayMetrics().density;
    	int padding = Math.round(10*scale);
    	body.setPadding(padding, padding, padding, padding);
		this.addView(body,bodyParams); // add to view
    }
    
    public void setFieldID(int field_id){
    	this.field_id = field_id;
    }
    public void setTitle(String title) {
    	this.title = title;
    }
    public void setDescription(String description){
    	this.description = description;
    }
        
    protected void drawTitle(){
    	if (title_view == null) {
    		title_view = new TextView(context);
    		title_view.setTextColor(Color.BLACK);
        	title_view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        	title_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        	title_view.setId(TITLE_ID);
        	LayoutParams params =new RelativeLayout.LayoutParams(
        			ViewGroup.LayoutParams.WRAP_CONTENT,
    				ViewGroup.LayoutParams.WRAP_CONTENT);
        	params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        	float scale = context.getResources().getDisplayMetrics().density;
        	int padding = Math.round(10*scale);
        	title_view.setPadding(padding, padding, padding, 0);
    		this.addView(title_view,params);
    	}
    	title_view.setText(title);
    	
    }
    
    protected void drawDescription(){
    	if (description_view == null) {
	    	description_view = new TextView(context);
	    	description_view.setId(DESCRIPTION_ID);
	    	description_view.setTextColor(Color.parseColor("#444444"));
	    	description_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
	    	LayoutParams params =new RelativeLayout.LayoutParams(
	    			ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
	    	params.addRule(RelativeLayout.BELOW,BODY_ID);
	    	float scale = context.getResources().getDisplayMetrics().density;
	    	int padding = Math.round(10*scale);
	    	description_view.setPadding(padding, 0, padding, padding);
			this.addView(description_view,params);
    	}
    	description_view.setText(description);
    }
    
    public abstract String getValue();
    
    public void setDataHandler(DataHandler data_handler){
    	this.data_handler = data_handler;
    }
    
    public void valueChanged(){
    	if (data_handler !=null) data_handler.valueChanged(getValue());
    	else dbUpdateValue();
    }
    
    public void loadValue(){
        if (data_handler != null) data_handler.loadValue(this);
        else loadFromDB(field_id);
    }
    
    public abstract void dbUpdateValue();
    
    public abstract void loadFromDB(int field_id);
    
    public abstract void addOrRemoveTitleViewAsNecessary();
    public abstract void addOrRemoveDescriptionViewAsNecessary();
    
    public interface DataHandler {
    	public void valueChanged(String value);
    	public void loadValue(WidgetBase widget);
    }
}