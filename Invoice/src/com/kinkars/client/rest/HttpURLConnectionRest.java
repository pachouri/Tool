package com.kinkars.client.rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.kinkars.database.sqlserver.ConnectMSSQLServer;
import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.sync.info.SyncInvoiceInfo;

public class HttpURLConnectionRest {
	final static Logger logger = Logger.getLogger(HttpURLConnectionRest.class);
    private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://localhost/invoice/public/webresources/mobile_app/payments/1";

	private static final String POST_URL = "http://localhost/REST-Api-with-Slim-PHP/public/webresources/mobile_app/clients";

	private static final String POST_PARAMS = "ext_client_id=9";

	private static void sendGET() throws IOException {
		GetPropertyValues prop = new GetPropertyValues();
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty( prop.getPropValues().getProperty("AUTH"),prop.getPropValues().getProperty("JWTOKEN"));
		con.setRequestProperty(prop.getPropValues().getProperty("USER_KEY"), prop.getPropValues().getProperty("USER_AGENT"));
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				System.out.println(response);
			}
			in.close();

			// print result
			logger.info(response.toString());
		} else {
			logger.info("GET request not worked");
		}

	}



	public static String sendPOST(String url,String postparams) throws IOException {
		String res=null;
		GetPropertyValues prop = new GetPropertyValues();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty( prop.getPropValues().getProperty("AUTH"),prop.getPropValues().getProperty("JWTOKEN"));
		con.setRequestProperty(prop.getPropValues().getProperty("USER_KEY"), prop.getPropValues().getProperty("USER_AGENT"));

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(postparams.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		logger.info("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				res=response.append(inputLine).toString();
			}
			in.close();

			// print result
			logger.info(response.toString());
			
		} else {
			logger.info("POST request not worked");
		}
		return res;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HttpURLConnectionRest sys= new HttpURLConnectionRest();
		sys.sendGET();
		
		
	}
}