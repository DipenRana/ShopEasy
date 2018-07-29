package com.example.shopeasy;


import com.example.shopeasy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeDelivery extends Activity implements OnClickListener{

	Button yes,no;
	static CustomerDetails cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.homedelevery);
		yes=(Button)findViewById(R.id.bYes);
		no=(Button)findViewById(R.id.bNo);
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		
	}
	
	public void onClick(View v) {
		
		if (v.getId() == R.id.bYes) {
			Intent intent = new Intent(this, AddressDetail.class);
			startActivity(intent);
			finish();
		}
		if (v.getId() == R.id.bNo) {
			cd = new CustomerDetails("home");
			BarcodeSender br = new BarcodeSender(Logo.con);
			br.send(cd);
			Intent intent = new Intent(this, MakePayment.class);
			intent.putExtra("Activity", "home");
			startActivity(intent);
			finish();
		}
		
	}
	
}
