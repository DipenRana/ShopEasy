package com.example.shopeasy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddressDetail extends Activity implements OnClickListener {

	EditText name, addr1, addr2, addr3, pincode, contact;
	Button save;
	static CustomerDetails cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addressdetail);
		name = (EditText) findViewById(R.id.etName);
		addr1 = (EditText) findViewById(R.id.et1stLine);
		addr2 = (EditText) findViewById(R.id.etArea);
		addr3 = (EditText) findViewById(R.id.etCity);
		pincode = (EditText) findViewById(R.id.etPincode);
		contact = (EditText) findViewById(R.id.etContact);
		save = (Button) findViewById(R.id.bSave);

		save.setOnClickListener(this);

	}

	public void onClick(View v) {
		cd = new CustomerDetails("addrdetail");
		if (v.getId() == R.id.bSave) {

			if (name.getText().toString().equals("")
					|| addr1.getText().toString().equals("")
					|| addr2.getText().toString().equals("")
					|| addr3.getText().toString().equals("")
					|| contact.getText().toString().equals("")
					|| pincode.getText().toString().equals("")) {
				new MessageDialog(this, "Message", "All fields are mandatory").alertDialog
						.show();
			} else {
				cd.Name = name.getText().toString();
				cd.Address1 = addr1.getText().toString();
				cd.Address2 = addr2.getText().toString();
				cd.Address3 = addr3.getText().toString();
				cd.Contact = contact.getText().toString();
				cd.Pincode = pincode.getText().toString();

				BarcodeSender br = new BarcodeSender(Logo.con);
				br.send(cd);
				
			      Intent i = new Intent(this, MakePayment.class);
			      	i.putExtra("Activity", "address");
					startActivity(i);
				finish();
			}
		}

	}

}
