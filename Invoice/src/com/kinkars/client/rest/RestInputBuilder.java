package com.kinkars.client.rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;

import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.sync.info.SyncCustomerInfo;
import com.kinkars.sync.info.bean.ClientInfo;


public class RestInputBuilder {
	final static Logger logger = Logger.getLogger(SyncCustomerInfo.class);
	public String syncClients(List<ClientInfo> clientInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"clients";
		for(int i=0; i<clientInfo.size(); i++){
			ClientInfo ci=clientInfo.get(i);	
			logger.info("Param"+ "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name());
		}
		System.out.println("URL"+url);
		//System.out.println("postparams"+postparams);
		return result;
	}
}
