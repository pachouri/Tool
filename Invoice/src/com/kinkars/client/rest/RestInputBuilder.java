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
import com.kinkars.sync.info.SyncInvoiceInfo;
import com.kinkars.sync.info.bean.ClientInfo;
import com.kinkars.sync.info.bean.FamilyInfo;


public class RestInputBuilder {
	final static Logger logger = Logger.getLogger(SyncInvoiceInfo.class);
	public String syncClients(List<ClientInfo> clientInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"clients";
		for(int i=0; i<clientInfo.size(); i++){
			ClientInfo ci=clientInfo.get(i);
			/*clientInfo.setClient_address_1(rs.getString("Address1"));
			clientInfo.setClient_address_2(rs.getString("Address2")+","+ rs.getString("Address3")+","+ rs.getString("Address4"));
			clientInfo.setClient_phone(rs.getString("TelNo"));
			clientInfo.setClient_fax(rs.getString("FAX"));
			clientInfo.setClient_email(rs.getString("EMAIL"));
			clientInfo.setClient_mobile(rs.getString("Mobile"));
			clientInfo.setClient_vat_id(rs.getString("GSTNo"));*/
			logger.info("Param"+ "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name()+"&client_address_1="+ci.getClient_address_1()
			+"&client_address_2="+ci.getClient_address_2()+"&client_phone="+ci.getClient_phone()
			+"&client_fax="+ci.getClient_fax()+"&client_email="+ci.getClient_mobile()+
			"&client_vat_id="+ci.getClient_vat_id()
		    );
		}
		return result;
	}
	public String syncFamilies(List<FamilyInfo> familyInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"families";
		for(int i=0; i<familyInfo.size(); i++){
			FamilyInfo fi=familyInfo.get(i);
			logger.info("Param"+ "ext_family_id="+fi.getExt_family_id()+"&family_name="+fi.getFamily_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_family_id="+fi.getExt_family_id()+"&family_name="+fi.getFamily_name());
		}
		return result;
	}
}
