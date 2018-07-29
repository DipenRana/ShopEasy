package com.example.shopeasy;

import Connection.Connection;
import Connection.ConnectionManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.EOFException;
import java.lang.NullPointerException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.PrintStream;
import java.security.spec.ECField;

public class Logo extends Activity {

	static Connection con;
	private Socket socket;
	private static final int SERVERPORT = 9180;
	private static final String SERVER_IP = "192.168.0.1";
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				new MessageDialog(Logo.this,"Connection Error","Connection not found").alertDialog.show();
			}
			
		};
		
		setContentView(R.layout.initialscreen);
		new Thread(new ClientThread()).start();
	}

	class ClientThread implements Runnable {
		public void run() {
			try {
				
				con = ConnectionManager.connect();
				con.setTimeout();
				if(con==null)
				  handler.sendEmptyMessage(0);
				else
				{
					Thread.sleep(2000);
					 finish();
				Intent i = new Intent(Logo.this, MainActivity.class);
				startActivity(i);
				finish();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
