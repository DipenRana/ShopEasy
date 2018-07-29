package com.example.shopeasy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutionException;

import com.example.shopeasy.MainActivity.Recieve;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import SEDatabase.DBDetails;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MakePayment extends Activity implements OnClickListener{

	TextView totalAmt;
	Button ret;
	float amt;
	String act,details,billno,date;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		
		
		totalAmt = (TextView)findViewById(R.id.tvAmt);
		ret=(Button)findViewById(R.id.bPayret);
		ret.setOnClickListener(this);
		Intent intent= getIntent();
		act=intent.getStringExtra("Activity");
		amt= MainActivity.Totalamount;
		totalAmt.setText("Rs "+amt+"\nShipping Charges(Rs.50)");
		try {
			details = new Recieve().execute().get();
			String arr[]=details.split("@");
			billno=arr[0];
			date=arr[1];
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// scan
	
		if (v.getId() == R.id.bPayret) {
			if(act.equals("address")){
				CustomerDetails cd= AddressDetail.cd;
				 Document document = new Document();
			      try
			      {
			    	  File path = Environment.getExternalStoragePublicDirectory(
			    	            Environment.DIRECTORY_PICTURES);
			         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+"/ShopEasyBill.pdf"));
			         document.open();
			         document.add(new Paragraph("ShopEasy Invocice Deatils."));
			         document.add(new Paragraph("\n\nBill NO:#"+ billno));
			         document.add(new Paragraph("\nDate:"+date));
			         document.add(new Paragraph("Name: " + cd.Name));
			         document.add(new Paragraph("Address:\n" + cd.Address1+"\n"+cd.Address2+cd.Address3+" Pin:"+cd.Pincode));
			         document.add(new Paragraph("Contact: " + cd.Contact));
			         document.add(new Paragraph("\nList of Items"));
			         int size= MainActivity.iName.size();

			         for(int i=0;i<size;i++)
			         {
			        	
			         document.add(new Paragraph("\n"+(i+1)+"." +MainActivity.iName.get(i)+"   "+MainActivity.iQty.get(i)+"   "+MainActivity.iPrice.get(i)));
			         }
			         amt+=50;
			         document.add(new Paragraph("\nShipping charges: Rs.50"));
			         document.add(new Paragraph("\nTotal: Rs." +amt));
			         document.add(new Paragraph("\n\n*************THANK YOU*****************"));
			        		 document.close();
			         writer.close();
			      } catch (DocumentException e)
			      {
			         e.printStackTrace();
			      } catch (FileNotFoundException e)
			      {
			         e.printStackTrace();
			      }
			}

		
			
			if(act.equals("home")){
				CustomerDetails cd= AddressDetail.cd;
				 Document document = new Document();
			      try
			      {
			    	  File path = Environment.getExternalStoragePublicDirectory(
			    	            Environment.DIRECTORY_PICTURES);
			         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+"/ShopEasyBill.pdf"));
			         document.open();
			         document.add(new Paragraph("ShopEasy Invocice Deatils."));
			         document.add(new Paragraph("\n\nBill NO:#"+billno));
			         document.add(new Paragraph("\nDate:"+date));
			         document.add(new Paragraph("\nList of Items"));
			         int size= MainActivity.iName.size();
			         float total= 0;
			        
			         for(int i=0;i<size;i++)
			         {
			        	
			         document.add(new Paragraph((i+1)+"." +MainActivity.iName.get(i)+"   "+MainActivity.iQty.get(i)+"   "+MainActivity.iPrice.get(i)));
			         }
			        
			         document.add(new Paragraph("\nTotal: Rs." +amt));
			         document.add(new Paragraph("\n\n*************THANK YOU*****************"));
			        		 document.close();
			         writer.close();
			      } catch (DocumentException e)
			      {
			         e.printStackTrace();
			      } catch (FileNotFoundException e)
			      {
			         e.printStackTrace();
			      }
			}
			
			Intent i = new Intent(this, Thankyou.class);
			i.putExtra("BillNo",""+billno);
			startActivity(i);
			finish();
		}
		
	}
	
	 class Recieve extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			DBDetails details = null;
			String Detailvalues = null;
			// TODO Auto-generated method stub
			BarcodeRecieverClient b_recieve = new BarcodeRecieverClient(
					Logo.con);
			try {
				details = b_recieve.recieve();
				if(details.msg.equals("bill")){
					Detailvalues= details.bill_no+"@"+details.date;
				}
				

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return Detailvalues;
			
		}
	}

	
}
