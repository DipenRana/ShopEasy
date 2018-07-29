package com.example.shopeasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Thankyou extends Activity{

	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thankyou);
		tv=(TextView)findViewById(R.id.tvOne);
		
		Intent i= getIntent();
 		
		tv.setText("Thank You for Shopping\n\n Your Bill No:"+i.getStringExtra("BillNo"));
	}

}
