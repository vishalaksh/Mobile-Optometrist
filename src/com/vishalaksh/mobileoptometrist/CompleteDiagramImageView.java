package com.vishalaksh.mobileoptometrist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CompleteDiagramImageView extends ImageView {

	private class FlingGestureDetector extends
			GestureDetector.SimpleOnGestureListener {

		int mLastX, mLastY;

		@Override
		public boolean onDown(MotionEvent e) {

			Log.d(getClass().getName(), "onDown called..");

		
			// Reset fling state
			return true;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {

			Log.d(getClass().getName(), "onScroll called..");

			
				if (e2.getAction() == MotionEvent.ACTION_MOVE) {
					if (!mIsDragging) {
						// Stop animation
						// mIsTouched = true;

						// Reconfigure scroll
						mIsDragging = true;
					}

					// for circle to remain within the view's limits
					int x = (int) e2.getX(), y = (int) e2.getY();
					/*
					 * if (e2.getX() < markRadius) { x = markRadius; } else {
					 * int maxX = getWidth() - markRadius; if (x > maxX) { x =
					 * maxX; } } if (e2.getY() < markRadius) { y = markRadius; }
					 * else { int maxY = getHeight() - markRadius; if (y > maxY)
					 * { y = maxY; } }
					 */

					int midy = (int) (height / 2);

					if (y < midy) {
						delta = midy - y;
					} else {
						delta = y - midy;
					}
		//new value of c upon touch
					c=(int)((((double)a/2))-(double)delta);
					
					if (x == mLastX && y == mLastY)
						return false;

					mLastX = x;
					mLastY = y;
				}
		
			return false;

		}



	}

	public interface MarkerChangeListener {

		public void onMeasureCalled();
		public void onDrawCalled();
	}

	/* private Paint paint_drag = new Paint(), paint_mark = new Paint(); */
	private Paint paint_slitHole = new Paint();
	private Paint paint_r = new Paint();
	private Paint paint_slit = new Paint();
	private Paint paint_y = new Paint();
	private Paint paint_variablelines = new Paint();
	// private Options opt;

	int  lineWidth = 2;
	
	int  slitWidth = 5;

	double width, height;
	int delta;
	int lensWidth,lensHeight,phoneWidth,phoneHeight,eyeWidth,eyeHeight;
	Point origin;
	Rect lensBounds,phoneBounds,eyebounds;
	int c, a, f, t;
	int focalLengthNormal;
	int focalLengthIncorrect ;
	int eyeRadius;
	int v;
	int slitOpeningWidth;
	int slitHeight;
	Bitmap bitmapphone,bitmapeye ;

	private MarkerChangeListener markerChangeListener;
	private GestureDetector mGestureDetector;
	
	private boolean mIsDragging = false;

	private static final long SPEED_BASE = 30;

	{

		paint_slitHole.setAntiAlias(true);
		paint_slitHole.setColor(Color.WHITE);
		paint_slitHole.setStyle(Paint.Style.FILL_AND_STROKE);
		paint_slitHole.setStrokeWidth(slitWidth);

		paint_r.setAntiAlias(true);
		paint_r.setColor(Color.RED);
		paint_r.setStyle(Paint.Style.STROKE);
		paint_r.setStrokeWidth(lineWidth);
		
		paint_slit.setAntiAlias(true);
		paint_slit.setColor(Color.BLACK);
		paint_slit.setStyle(Paint.Style.FILL_AND_STROKE);
		paint_slit.setStrokeWidth(slitWidth);
		
		paint_y.setAntiAlias(true);
		paint_y.setColor(Color.YELLOW);
		paint_y.setStyle(Paint.Style.FILL_AND_STROKE);
		paint_y.setStrokeWidth(lineWidth);
		//paint_y.setPathEffect(new DashPathEffect(new float[] {20,10}, 0));
		
		
		paint_variablelines.setAntiAlias(true);
		paint_variablelines.setColor(Color.MAGENTA);
		paint_variablelines.setStyle(Paint.Style.STROKE);
		paint_variablelines.setStrokeWidth(lineWidth);

		mGestureDetector = new GestureDetector(new FlingGestureDetector());
		origin = new Point(0, 0);

	}

	public CompleteDiagramImageView(Context context) {
		super(context);
		// init();
	}

	public CompleteDiagramImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init();
	}

	public CompleteDiagramImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// init();
	}


	@Override
	protected void onDraw(Canvas canvas) {

		Log.d(getClass().getName(), "currently in onDraw(Canvas canvas) block");
		super.onDraw(canvas);


	
		// verical lines
		//screen
		/*canvas.drawLine(origin.x + 0f, 0f, origin.x + 0f, (float) height,
				paint_r);*/
		Log.d(getClass().getName(), "phoneBounds:"+phoneBounds.toString());
		
		canvas.drawBitmap(bitmapphone, null, phoneBounds, null);
		
	
		
		
		
		
		/*Drawable d = getContext().getResources().getDrawable(R.drawable.lens);  
		Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
		*/
		Log.d(getClass().getName(), "lensBounds:"+lensBounds.toString());
		canvas.drawBitmap(bitmapeye, null, eyebounds, null);
		//canvas.drawBitmap(bitmap, null, lensBounds, null);
		/*	canvas.drawLine(origin.x + f + t, 0f, origin.x + f + t, (float) height,
				paint_r);*/
		
/*		canvas.drawLine(origin.x + f + t + focalLengthNormal, origin.y
				- eyeRadius, origin.x + f + t + focalLengthNormal, origin.y
				+ eyeRadius, paint_r);
*/
		// horizontal dotted line
		canvas.drawLine(origin.x, origin.y - (a / 2), origin.x + f + t,
				origin.y - (a / 2), paint_r);
		canvas.drawLine(origin.x, origin.y + (a / 2), origin.x + f + t,
				origin.y + (a / 2), paint_r);

		// bent line
		canvas.drawLine(origin.x + f + t, origin.y - (a / 2), origin.x + f + t
				+ focalLengthIncorrect, origin.y, paint_r);
		canvas.drawLine(origin.x + f + t, origin.y + (a / 2), origin.x + f + t
				+ focalLengthIncorrect, origin.y, paint_r);
	
		// draw lines with given value of c
		int valYFinal=(f+t)*(c)/f+(a/2-c);
		Log.d(getClass().getName(), "valYFinal:"+valYFinal);
		
		canvas.drawLine(origin.x, origin.y - (a / 2) + c, origin.x + f + t,
				origin.y -valYFinal, paint_variablelines);
		canvas.drawLine(origin.x, origin.y + (a / 2) - c, origin.x + f + t,
				origin.y +valYFinal, paint_variablelines);
		
		
		
		int exradist=(int)((((double)(a/2)-(double)c)/(double)c)*f);
		
		int umag=exradist+f+t;
		Log.d(getClass().getName(), "umag:"+umag);
		v=focalLengthIncorrect*((-1)*umag)/(focalLengthIncorrect-umag);
		Log.d(getClass().getName(), "v:"+v);
		canvas.drawLine(origin.x + f + t, origin.y - valYFinal, origin.x + f
				+ t + v, origin.y, paint_variablelines);
		canvas.drawLine(origin.x + f + t, origin.y + valYFinal, origin.x + f
				+ t + v, origin.y, paint_variablelines);
		
		
		//draw lines of object
		if(exradist>0){
		canvas.drawLine(origin.x, origin.y - (a / 2) + c, (int)(origin.x -exradist),
				origin.y, paint_y);
		canvas.drawLine(origin.x, origin.y + (a / 2) - c, (int)(origin.x -exradist),
				origin.y , paint_y);
		
		
		//slit
		canvas.drawLine(origin.x + f, origin.y-slitHeight/2, origin.x + f, origin.y+slitHeight/2, paint_slit);
		//draw slit openings
				canvas.drawLine(origin.x + f, origin.y - (a / 2)-slitOpeningWidth, origin.x + f, origin.y - (a / 2)+slitOpeningWidth, paint_slitHole);
				canvas.drawLine(origin.x + f, origin.y + (a / 2)-slitOpeningWidth, origin.x + f, origin.y + (a / 2)+slitOpeningWidth, paint_slitHole);
		}
		
		
		 markerChangeListener.onDrawCalled();

	}



	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		Log.d(getClass().getName(), "onSizeChanged() called..");
		
		width = (double) this.getMeasuredWidth();
		height = (double) this.getMeasuredHeight();
		
		Log.d(getClass().getName(), "width:" + width);
		Log.d(getClass().getName(), "height:" + height);
		 markerChangeListener.onMeasureCalled();
		super.onSizeChanged(w, h, oldw, oldh);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(getClass().getName(),
				"currently in onTouchEvent(MotionEvent event) block");
		mGestureDetector.onTouchEvent(event);
		invalidate();

		return true;
	}

	public void setOnMarkerChangeListener(
			MarkerChangeListener markerChangeListner) {
		this.markerChangeListener = markerChangeListner;
	}
}
