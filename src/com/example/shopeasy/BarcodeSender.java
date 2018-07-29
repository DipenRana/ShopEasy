package com.example.shopeasy;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import Connection.Connection;

public class BarcodeSender implements Serializable{
	
	 private Connection con;
	 
	 public BarcodeSender(Connection con){
		 this.con=con;
	 }

	public void send(CustomerDetails cd) {
		 try {
	            ObjectOutputStream oos = new ObjectOutputStream(con.getOutputStream());
	            oos.writeObject(cd);
	            oos.flush();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	 
}
