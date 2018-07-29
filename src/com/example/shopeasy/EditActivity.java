package com.example.shopeasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditActivity extends Activity{
	 
	int qty;
	Button add,sub,submit,remove;
	TextView display;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		qty = 1 ;
		add = (Button)findViewById(R.id.bAdd);
		sub= (Button)findViewById(R.id.bSub);
		submit= (Button)findViewById(R.id.bSubmit);
		remove= (Button)findViewById(R.id.bRemove);
		display =(TextView)findViewById(R.id.tvDispaly);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				qty++;
				display.setText(" "+qty);
			}
		});
		sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(qty>1)
			 	qty--;
			 	display.setText(" "+qty);
			}
		});
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			  Intent I= new Intent();
			  I.putExtra("Data",qty);
			  setResult(1,I);
			  finish();
			}
		});
		remove.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			  Intent I= new Intent();
			  setResult(2,I);
			  finish();
			}
		});
		
	}

	
}
