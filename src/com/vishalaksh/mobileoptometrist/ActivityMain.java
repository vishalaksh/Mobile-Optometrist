package com.vishalaksh.mobileoptometrist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.vishalaksh.mobileoptometrist.R;

public class ActivityMain extends Activity implements Constants{

	CustomImageView civ;
	Button bnext;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        civ = (CustomImageView) findViewById(R.id.imageView1);
        bnext=(Button) findViewById(R.id.buttonNext);
        
        
        bnext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int del=civ.delta;
				
				Intent i = new Intent(ActivityMain.this, ActivityCompleteDiagram.class);
				i.putExtra(EXTRA_DELTA, del);
				startActivity(i);
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
