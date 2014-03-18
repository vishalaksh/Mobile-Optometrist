package com.vishalaksh.mobileoptometrist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.vishalaksh.mobileoptometrist.R;

public class ActivityMenu extends Activity {
	
	Button bapp,bdemo,bcalc,bsubj,bcolor;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		bapp=(Button) findViewById(R.id.Button_app);
		bapp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i= new Intent(ActivityMenu.this, ActivityMain.class);
			startActivity(i);
				
			}
		});
		
		
		
		
		bdemo=(Button) findViewById(R.id.Button_demo);
		bdemo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i= new Intent(ActivityMenu.this, ActivityCompleteDiagram.class);
				startActivity(i);
				
			}
		});
		
		
		
		bcalc=(Button) findViewById(R.id.Button_calc);
		bcalc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i= new Intent(ActivityMenu.this, ActivityCalculation.class);
				startActivity(i);
				
			}
		});
		
		
		bsubj=(Button) findViewById(R.id.Button_subj);
		bsubj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i= new Intent(ActivityMenu.this, ActivitySubjectiveTest.class);
				startActivity(i);
				
			}
		});
		
		bcolor=(Button) findViewById(R.id.Button_blind);
		bcolor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i= new Intent(ActivityMenu.this, ActivityColorBlindTest.class);
			startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
