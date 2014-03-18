package com.vishalaksh.mobileoptometrist;

import java.util.ArrayList;

import org.w3c.dom.Comment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CustomImageView extends ImageView {


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
	}

	/* private Paint paint_drag = new Paint(), paint_mark = new Paint(); */
	private Paint paint_w = new Paint();
	private Paint paint_r = new Paint();
	// private Options opt;

	int tempLineWidth = 10, lineWidth = 2;

	double width, height;
	public int delta;
	public int currX, currY;
	Point origin;
	int c = 50, a = 50, f = 50, t = 50;
	int focalLengthNormal = 25;
	int focalLengthIncorrect = 15;
	int eyeRadius = 20;

	private MarkerChangeListener markerChangeListener;
	private GestureDetector mGestureDetector;
	private boolean mIsTouched = false;
	private boolean mIsDragging = false;
	private boolean isCustomViewTouchable = true;

	private boolean isMarkerDisplayed = true;
	boolean isImageViewReadOnly = false;

	private static final long SPEED_BASE = 30;

	{

		paint_w.setAntiAlias(true);
		paint_w.setColor(Color.MAGENTA);
		paint_w.setStyle(Paint.Style.FILL);
		paint_w.setStrokeWidth(lineWidth);

		paint_r.setAntiAlias(true);
		paint_r.setColor(Color.RED);
		paint_r.setStyle(Paint.Style.FILL);
		paint_r.setStrokeWidth(lineWidth);

		mGestureDetector = new GestureDetector(new FlingGestureDetector());
		origin = new Point(0, 0);

	}

	public CustomImageView(Context context) {
		super(context);
		// init();
	}

	public CustomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init();
	}

	public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// init();
	}

	public boolean isCustomViewTouchable() {
		return isCustomViewTouchable;
	}

	public boolean isMarkerDisplayed() {
		return isMarkerDisplayed;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Log.d(getClass().getName(), "currently in onDraw(Canvas canvas) block");
		super.onDraw(canvas);

	/*	int midy = (int) (height / 2);
		
		if (currY < midy) {
			delta = midy - currY;
		} else {
			delta = currY - midy;
		}*/

		float ycood1 = (float) (height / 2 + delta);
		float ycood2 = (float) (height / 2 - delta);

		Log.d(getClass().getName(), "drawing 2 horizontal lines with ycoords(abs):" + ycood1+","+ycood2);
		

		canvas.drawLine(0f, ycood1, (float) width, ycood1, paint_w);
		canvas.drawLine(0f, ycood2, (float) width, ycood2, paint_w);

			}



	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		Log.d(getClass().getName(), "onSizeChanged() called..");
		// markerChangeListener.onMeasureCalled();
		width = (double) this.getMeasuredWidth();
		height = (double) this.getMeasuredHeight();
		origin.set(0, (int) height / 2);
		a = f = t = (int) ((width) / 3);
		c = (int) (width / 5);
		Log.d(getClass().getName(), "width:" + width);
		Log.d(getClass().getName(), "height:" + height);

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


}
