package com.example.shopeasy;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

import SEDatabase.DBDetails;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements OnClickListener {

	private ImageButton scanBtn, payment;
	private TextView total;
	static List<String> iName=null, iQty=null, iPrice=null, iAmt=null;
	ListView list=null;
	String scanContent = null;
	static String b_code = null;
	DBDetails details = null;

	int pos = 0;
	static float Totalamount =(float) 0.00;
	MyAdapter ma;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		scanBtn = (ImageButton) findViewById(R.id.scan_button);

		total = (TextView) findViewById(R.id.tvTotal);
		payment = (ImageButton) findViewById(R.id.bPay);
		scanBtn.setOnClickListener((OnClickListener) this);
		payment.setOnClickListener((OnClickListener) this);
		iName = new ArrayList<String>();
		iPrice = new ArrayList<String>();
		iQty = new ArrayList<String>();
		iAmt = new ArrayList<String>();

		list = (ListView) findViewById(android.R.id.list);
		ma = new MyAdapter(this);
		list.setAdapter(ma);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Class ourClass;
				pos = position;
				try {
					ourClass = Class
							.forName("com.example.shopeasy.EditActivity");

					Intent ourI = new Intent(MainActivity.this, ourClass);
					startActivityForResult(ourI, 3);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// scan
		if (v.getId() == R.id.scan_button) {
			Intent intent = new Intent(this, ZBarScannerActivity.class);
			startActivityForResult(intent, 1);
		}
		if (v.getId() == R.id.bPay) {
			Intent i = new Intent(this, HomeDelivery.class);
			startActivity(i);
			finish();
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// retrieve scan result

		int qty = 1;
		String details = null;

		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				scanContent = data.getStringExtra(ZBarConstants.SCAN_RESULT);
				b_code = scanContent;
				Integer scanFormat = data.getIntExtra(
						ZBarConstants.SCAN_RESULT_TYPE, 0);

				try {
					Class ourClass;
					ourClass = Class
							.forName("com.example.shopeasy.SetQuantity");
					Intent ourIntent = new Intent(MainActivity.this, ourClass);
					startActivityForResult(ourIntent, 2);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "No barocode scanned", Toast.LENGTH_LONG)
						.show();
			}
		}
		if (requestCode == 2) {
			if (resultCode == RESULT_OK) {
				qty = data.getIntExtra("Data", 1);
				try {
					BarcodeSender br = new BarcodeSender(Logo.con);
					CustomerDetails cd = new CustomerDetails("barcode");
					cd.barcode = b_code + "@" + qty;
					br.send(cd);

					details = new Recieve().execute().get();
					// Toast.makeText(this, details, Toast.LENGTH_LONG).show();

				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (details.equals("Wrong")) {
					Toast.makeText(this, "Wrong barcode scanned!",
							Toast.LENGTH_SHORT).show();
				} else {
					qty = data.getIntExtra("Data", 1);
					String arr[] = details.split("@");
					float amt;
					amt = Float.parseFloat(arr[1]);
					iAmt.add(amt + "");
					amt = amt * qty;
					amt = amt * 100;
					amt = (int) amt;
					amt = amt / 100;
					Totalamount += amt;
					arr[1] = Float.toString(amt);
					iName.add(arr[0]);
					iPrice.add("Rs:" + arr[1]);
					iQty.add("Quantity:" + qty);
					ma.notifyDataSetChanged();
					total.setText("Total: " + Totalamount);
					
					
				}

			}
		}
		if (requestCode == 3) {
			if (resultCode == 1) {
				int qtyx = data.getIntExtra("Data", 1);
				String p = iPrice.get(pos),name=iName.get(pos),qty1=iQty.get(pos);
				
				String arr1[] = qty1.split(":");
				String arr[] = p.split(":");
				float amt, prc;
				prc = Float.parseFloat(arr[1]);
				Totalamount -= prc;

				amt = Float.parseFloat(iAmt.get(pos));
				amt = amt * qtyx;
				amt = amt * 100;
				amt = (int) amt;
				amt = amt / 100;
				Totalamount += amt;
				iPrice.set(pos, "Rs:" + amt);
				iQty.set(pos, "Quantity:" + qtyx);
				ma.notifyDataSetChanged();
				total.setText("Total: " + Totalamount);
				
				BarcodeSender br = new BarcodeSender(Logo.con);
				CustomerDetails cd = new CustomerDetails("edit");
				cd.Name =name;
				cd.qty=arr1[1];
				cd.Nqty=qtyx+"";
				br.send(cd);
			}
			if (resultCode == 2) {
				String p = iPrice.get(pos),name= iName.get(pos),qty1=iQty.get(pos);
				String arr[] = p.split(":");
				String arr1[] = qty1.split(":");
				Float prc = Float.parseFloat(arr[1]);
				Totalamount -= prc;
				
				iName.remove(pos);
				iPrice.remove(pos);
				iQty.remove(pos);
				iAmt.remove(pos);
				ma.notifyDataSetChanged();
				total.setText("Total: " + Totalamount);
				
				BarcodeSender br = new BarcodeSender(Logo.con);
				CustomerDetails cd = new CustomerDetails("remove");
				cd.Name =name;
				cd.qty=arr1[1];
				br.send(cd);

			}
		}

	}

	public class Recieve extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			
			String Detailvalues = null;
			// TODO Auto-generated method stub
			BarcodeRecieverClient b_recieve = new BarcodeRecieverClient(
					Logo.con);
			try {
				details = b_recieve.recieve();
				if (details.msg.equals("wrong"))
					Detailvalues = "Wrong";
				else if(details.msg.equals("right"))
					Detailvalues = details.bcodedetail;

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return Detailvalues;
		}

	}

	class MyAdapter extends BaseAdapter {
		Context context;

		public MyAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			return iName.size();
		}

		@Override
		public Object getItem(int position) {
			return iName.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = null;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.customlistadapter, parent,
						false);
			} else {
				row = convertView;
			}
			TextView name = (TextView) row.findViewById(R.id.tvIname);
			TextView price = (TextView) row.findViewById(R.id.tvPrice);
			TextView qty = (TextView) row.findViewById(R.id.tvIqty);

			name.setText(iName.get(position));
			price.setText(iPrice.get(position));
			qty.setText(iQty.get(position));

			return row;
		}

	}

	

}
