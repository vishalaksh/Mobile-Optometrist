package com.vishalaksh.mobileoptometrist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.vishalaksh.mobileoptometrist.R;

public class ActivityCalculation extends Activity {

	double c,a,f,t;
	EditText etC,etA,etF,etT;
	Button bCalc;
	TextView tvAns;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculation);
		
		etC=(EditText)findViewById(R.id.editTextC);
		etA=(EditText)findViewById(R.id.editTextA);
		etF=(EditText)findViewById(R.id.editTextF);
		etT=(EditText)findViewById(R.id.editTextT);
		bCalc=(Button) findViewById(R.id.buttonCalculate);
		tvAns=(TextView) findViewById(R.id.textViewAns);
		
		bCalc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				c=Double.valueOf(etC.getText().toString());
				a=Double.valueOf(etA.getText().toString()); 
				f=Double.valueOf(etF.getText().toString());
				t=Double.valueOf(etT.getText().toString());
				
				Double ans = getD(c, a, f, t);
				tvAns.setVisibility(View.VISIBLE);
				tvAns.setText(String.valueOf(ans));
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_calculation, menu);
		return true;
	}
	
	public static double getD(double c,double a,double f, double t){
		double ans;
		
		ans=1000/(f*(a/2)/c+t);
		return ans;
	}

}
