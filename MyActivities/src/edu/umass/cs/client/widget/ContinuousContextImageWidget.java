package edu.umass.cs.client.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import java.util.LinkedList;

import edu.umass.cs.client.R;
import edu.umass.cs.client.widget.ContextImageWidget.RollingHistoryView;

/**
 * ContextImageWidget.java
 * @author musthag
 */
public class ContinuousContextImageWidget extends ContextImageWidget
{
	private float min = Integer.MAX_VALUE;
	private float max = Integer.MIN_VALUE;
	protected int height = 0;
	public ContinuousContextImageWidget(Context context, float min, float max, int height, LinkedList<Float> history) {
		super(context);
    	drawContainer();
    	drawImage();
    	this.min =min;
    	this.max = max;
    	this.height = height;
    	drawHistory(history);
	}
	
	@Override
	protected void drawHistory(LinkedList history){
		history_view = new ContinuousRollingHistoryView(context);
		if (history !=null) {
			history_view.setHistory(history);
		}
		this.history_view.setId(HISTORY_ID);
    	LayoutParams params =new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
    	params.addRule(RelativeLayout.BELOW,CONTAINER_ID);
    	params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		body.addView(this.history_view, params);
	}
	public class ContinuousRollingHistoryView extends RollingHistoryView{
		private final String TAG = "CCRolling";
		
		public ContinuousRollingHistoryView(Context context) {
			super(context);
		}

    	@Override
        public void onDraw(Canvas canvas) {
//    		Log.d("CONTINUOUSROLLING","called");
    		int xoffset =parentWidth/SIZE/2;
    		int prevX = -1;
    		float prevY= -1;
    		for(Object state : history){
    			float value = (Float)state;
    			float y = height - (value - min)/(max-min)*height;
                canvas.drawCircle(xoffset,y,DOT,paint);
                if (prevX >0  && prevY >0){
                	canvas.drawLine(prevX, prevY, xoffset, y, paint);
                }
//                Log.d(TAG,"x:" + xoffset + " y:" + y + " pW: " + parentWidth);
                prevX = xoffset;
            	prevY = y;
                xoffset+=parentWidth/SIZE;
    		}
        }
    	@Override
    	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
//    		Log.d("HISTORY", "MEASURING MEASURING MEASURING MEASURING MEASURING MEASURING MEASURING MEASURING MEASURING ");
    		parentWidth = MeasureSpec.getSize(widthMeasureSpec);
    		int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
//    		Log.d("ROLING", "Measured is : " + parentWidth + " height: " + parentHeight);
    		setMeasuredDimension(widthMeasureSpec,height);
    	}
    	
    }
}