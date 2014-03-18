package com.vishalaksh.mobileoptometrist;

import java.text.DecimalFormat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.vishalaksh.mobileoptometrist.R;
import com.vishalaksh.mobileoptometrist.CompleteDiagramImageView.MarkerChangeListener;

public class ActivityCompleteDiagram extends Activity implements Constants {

	int del;

	CompleteDiagramImageView diagImageView;
	TextView tvD,tvhead;
	int mindistGreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		del = getIntent().getIntExtra(EXTRA_DELTA, 0);
		setContentView(R.layout.activity_complete_diagram);

		tvD = (TextView) findViewById(R.id.textViewD);
		tvhead=(TextView) findViewById(R.id.textViewPowerHeading);
		
		diagImageView = (CompleteDiagramImageView) findViewById(R.id.imageViewDiag);

		diagImageView.setOnMarkerChangeListener(new MarkerChangeListener() {

			@Override
			public void onMeasureCalled() {
				double ratio = .7;
				diagImageView.origin.set((int) ((diagImageView.width / 2.5)),
						(int) ((diagImageView.height / 2)));
				diagImageView.a = (int) (((diagImageView.width) / 5) * ratio);
				diagImageView.f = (int) (((diagImageView.width) / 4) * ratio);
				diagImageView.t = (int) (((diagImageView.width) / 5) * ratio);

				// set the value of c
				diagImageView.c = (int) ((((diagImageView.a) / 2) - del) * ratio);
				

				diagImageView.focalLengthIncorrect = (int) (((diagImageView.width) / 4) * ratio);
				diagImageView.focalLengthNormal = (int) (((diagImageView.width) / 3) * ratio);

				diagImageView.eyeRadius = (int) (((double)(diagImageView.width) /(double) 2) * ratio);

			
				diagImageView.lensWidth = (int) (((diagImageView.width) / 10));
				diagImageView.lensHeight = (int) ((((double) diagImageView.height) / (double) 2));

				diagImageView.lensBounds = new Rect(diagImageView.origin.x
						+ diagImageView.f + diagImageView.t
						- (diagImageView.lensWidth / 2), diagImageView.origin.y
						- (diagImageView.lensHeight / 2),
						diagImageView.origin.x + diagImageView.f
								+ diagImageView.t
								+ (diagImageView.lensWidth / 2),
						diagImageView.origin.y + (diagImageView.lensHeight / 2));
				
				
				//setting eye
				Drawable deye = getResources().getDrawable(R.drawable.eye_blue);  
				
				diagImageView.bitmapeye = ((BitmapDrawable)deye).getBitmap();
				
				//diagImageView.eyeHeight = (int) ((double)diagImageView.height/(double)1.2);
				diagImageView.eyeHeight = diagImageView.eyeRadius ;
				double newEyeRatio= ((double)(diagImageView.eyeHeight)/(double)diagImageView.bitmapeye.getHeight());
				diagImageView.eyeWidth = (int) (diagImageView.bitmapeye.getWidth()*(newEyeRatio/(double)1.25));
			
				int eyeRight=diagImageView.origin.x + diagImageView.f + diagImageView.t + diagImageView.focalLengthNormal;
				diagImageView.eyebounds=new Rect(eyeRight-diagImageView.eyeWidth, diagImageView.origin.y-diagImageView.eyeHeight/2,eyeRight , diagImageView.origin.y+diagImageView.eyeHeight/2);

				//setting some other values
				mindistGreen = diagImageView.lensWidth / 5;
				diagImageView.slitOpeningWidth = diagImageView.lensWidth / 5;
				diagImageView.slitHeight = diagImageView.lensHeight;

				//setting phone
				Drawable dphone = getResources().getDrawable(R.drawable.phone);  
				
				diagImageView.bitmapphone = ((BitmapDrawable)dphone).getBitmap();
				
				
				diagImageView.phoneHeight = (int) ((double)diagImageView.height/(double)1.2);
				double newPhoneRatio= ((double)(diagImageView.phoneHeight)/(double)diagImageView.bitmapphone.getHeight());
				diagImageView.phoneWidth = (int) (diagImageView.bitmapphone.getWidth()*newPhoneRatio);
			
				diagImageView.phoneBounds = new Rect(diagImageView.origin.x
						- diagImageView.phoneWidth , diagImageView.origin.y
						- diagImageView.phoneHeight / 2, diagImageView.origin.x, diagImageView.origin.y
						+ diagImageView.phoneHeight / 2);

				diagImageView.invalidate();

			}

			@Override
			public void onDrawCalled() {
				double ans = getD(diagImageView.c, diagImageView.a,
						diagImageView.f, diagImageView.t);

				tvD.setText(new DecimalFormat("#.###").format(ans)+" Dioptre");

				if (diagImageView.v > (diagImageView.focalLengthNormal - mindistGreen)
						&& diagImageView.v < (diagImageView.focalLengthNormal + mindistGreen)) {
					tvD.setTextColor(Color.GREEN);
					tvhead.setVisibility(View.VISIBLE);
				} else {
					tvD.setTextColor(Color.BLACK);
					tvhead.setVisibility(View.GONE);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_complete_diagram, menu);
		return true;
	}

	public static double getD(double c, double a, double f, double t) {
		double ans;

		ans = 1000 / (f * (a / 2) / c + t);
		return ans;
	}

}
