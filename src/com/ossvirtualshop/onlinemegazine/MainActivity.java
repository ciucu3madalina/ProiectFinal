package com.ossvirtualshop.onlinemegazine;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {
    
	Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	 //Toast.makeText(getApplicationContext(), "Button clicked!!!", Toast.LENGTH_LONG).show();
            	Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);

            }
        });
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
