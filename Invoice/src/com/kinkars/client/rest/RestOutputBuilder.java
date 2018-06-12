package com.kinkars.client.rest;

import java.io.IOException;
import org.apache.log4j.Logger;
import com.kinkars.database.sqlserver.GetPropertyValues;


public class RestOutputBuilder {
	final static Logger logger = Logger.getLogger(RestOutputBuilder.class);
	public String syncInvoice(String id) throws IOException{
		String result =null;
		String APIName="invoices";
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+""+APIName+"/"+id;
		result=HttpURLConnectionRest.sendGET(url);
		return result;
	}
	public String syncPayment(String id) throws IOException{
		String result =null;
		String APIName="payments";
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+""+APIName+"/"+id;
		result=HttpURLConnectionRest.sendGET(url);
		return result;
	}
}
