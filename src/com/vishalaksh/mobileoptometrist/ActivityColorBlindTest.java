package com.vishalaksh.mobileoptometrist;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.vishalaksh.mobileoptometrist.R;

public class ActivityColorBlindTest extends Activity {

	private static final ColorBlindType[] items = { 
		
		new ColorBlindType(R.drawable.pic1, "12", "12", "12"),
		new ColorBlindType(R.drawable.pic2, "8", "3", "X"),
		new ColorBlindType(R.drawable.pic3, "5", "2","X"),
		new ColorBlindType(R.drawable.pic4, "29", "70","X"),
		new ColorBlindType(R.drawable.pic5, "74", "21","X"),
		new ColorBlindType(R.drawable.pic6, "7", "X","X"),
		new ColorBlindType(R.drawable.pic7, "45", "X","X"),
		new ColorBlindType(R.drawable.pic8, "2", "X","X"),
		new ColorBlindType(R.drawable.pic9, "X", "2","X"),
		new ColorBlindType(R.drawable.pic10, "16", "X", "X"),
		new ColorBlindType(R.drawable.pic11, "traceable", "X","X")
		
		};

	
	Button bStop, bNext;
	ImageView iv;
	int counter = 0;
	static final float maxSize = 100f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_colorblind_test);
		

		bStop = (Button) findViewById(R.id.buttonShow);
		bNext = (Button) findViewById(R.id.buttonNext1);
		iv=(ImageView) findViewById(R.id.imageViewcb);

		bStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				ColorBlindType cb1 = items[counter];
				
				StringBuilder sb=new StringBuilder();
				
				sb.append("Normal Person: "+cb1.normalPerson+"\n\n");
				sb.append("Person with Red-Green Deficiencies: "+cb1.redGreen+"\n\n");
				sb.append("Person with Total Color Blindness: "+cb1.total+"\n");
				
				
				
				Toast.makeText(getApplicationContext(),
						sb.toString(), Toast.LENGTH_SHORT)
						.show();

			}
		});

		bNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// inc counter
				// get string
				// set font size
				// set text

				counter++;
				if (counter < items.length) {
					ColorBlindType cb2 = items[counter];
					
					iv.setImageDrawable(getResources().getDrawable(cb2.id));
					
				} else {
					Toast.makeText(
							getApplicationContext(),
							"You have successfully completed Color Blindness Test!",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		ColorBlindType cb = items[0];
		iv.setImageDrawable(getResources().getDrawable(cb.id));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_subjective_test, menu);
		return true;
	}

}
