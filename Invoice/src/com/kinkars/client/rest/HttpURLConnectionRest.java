package com.kinkars.client.rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.kinkars.database.sqlserver.GetPropertyValues;

public class HttpURLConnectionRest {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://localhost/REST-Api-with-Slim-PHP/public/webresources/mobile_app/ping";

	private static final String POST_URL = "http://localhost/REST-Api-with-Slim-PHP/public/webresources/mobile_app/clients";

	private static final String POST_PARAMS = "ext_client_id=9";

	public static void main(String[] args) throws IOException {

		sendGET();
		System.out.println("GET DONE");
		//	sendPOST();
		System.out.println("POST DONE");
	}

	private static void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
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
		System.out.println("POST Response Code :: " + responseCode);

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
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
		return res;
	}
}