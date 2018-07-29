package com.example.shopeasy;

import java.io.EOFException;
import SEDatabase.DBDetails;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import Connection.Connection;

public class BarcodeRecieverClient {

	private Connection con;
	Context c;

	public BarcodeRecieverClient(Connection server) {
		this.con = server;
		this.c = c;
	}

	public DBDetails recieve() throws IOException, EOFException {
		// Scanner sc=new Scanner(con.getInputStream());
		while (true) {
			DBDetails details;
			try {
				ObjectInputStream ois = new ObjectInputStream(
						con.getInputStream());

				details = (DBDetails) ois.readObject();
				return details;

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				//return e.getMessage();
			}

		
		}
	}

}
