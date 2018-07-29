package com.example.shopeasy;

import java.io.Serializable;

public class CustomerDetails implements Serializable{

	public String Name=null,Address1=null,Address2=null,Address3=null,Pincode=null,Contact=null,msg=null,barcode=null,qty=null;
	public String Nqty=null;
	
	
	public CustomerDetails(String msg) {
	    this.msg=msg;
	}
}
	

