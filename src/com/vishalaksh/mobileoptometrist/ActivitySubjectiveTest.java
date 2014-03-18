package com.vishalaksh.mobileoptometrist;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.vishalaksh.mobileoptometrist.R;

public class ActivitySubjectiveTest extends Activity {

	private static final String[] items = { "F", "B C", "P T E O", "B Z F E D",
			"O F C L T B", "T E P O L F D Z", "L P C T Z D B F E O", "Z O E C F L D P B T", "E T O L E B Z E F D C",
			"A I O F M C K A Q L I J X E F A B" };

	TextView tvletter;
	Button bStop, bNext;
	int counter=0;
	float size;
	static final float maxSize=100f; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_subjective_test);
		tvletter = (TextView) findViewById(R.id.textViewLetters);

		bStop = (Button) findViewById(R.id.buttonStop);
		bNext = (Button) findViewById(R.id.buttonNext);
		
		bStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//calculate score
		//view score
				//TODO size should be in metres
				double theta=(((double)size/(double)10000)/(double)6);
				double powDecimal=(double)5/((double)theta*(double)60);
				String powsnellNr="6";
				double powsnellDr=(double)6*(double)60*(double)theta/(double)5;
				
				StringBuilder sb=new StringBuilder();
				sb.append(powsnellNr+"/"+new DecimalFormat("#.###").format(powsnellDr)+" (Snellen Notation) \n");
				sb.append(new DecimalFormat("#.###").format(powDecimal)+" (Decimal Notation)");
				
				
				Toast.makeText(getApplicationContext(), "Your eye power is: \n"+sb.toString(), Toast.LENGTH_LONG).show();
				
			}
		});
		
		bNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//inc counter
				//get string
				//set font size
				//set text
				
				counter++;
				if (counter<items.length) {
					String letter = items[counter];
					size= maxSize
							/ (counter + 1);
					tvletter.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
					tvletter.setText(letter);
				}else{
					Toast.makeText(getApplicationContext(), "You have successfully completed Subjective Eye Test!", Toast.LENGTH_LONG).show();
				}
				
				
			}
		});
		
		String letter=items[0];
		size=maxSize;
		tvletter.setTextSize(TypedValue.COMPLEX_UNIT_SP, maxSize);
		tvletter.setText(letter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_subjective_test, menu);
		return true;
	}

}
