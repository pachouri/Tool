package com.kinkars.client.rest;

import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.sync.info.bean.ClientInfo;

public class RestOutputBuilder {
	final static Logger logger = Logger.getLogger(RestOutputBuilder.class);
	public String syncClients(List<ClientInfo> clientInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"clients";
		for(int i=0; i<clientInfo.size(); i++){
			ClientInfo ci=clientInfo.get(i);
			
			logger.info("Param"+ "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name()+"&client_address_1="+ci.getClient_address_1()
			+"&client_address_2="+ci.getClient_address_2()+"&client_phone="+ci.getClient_phone()
			+"&client_fax="+ci.getClient_fax()+"&client_email="+ci.getClient_mobile()+
			"&client_vat_id="+ci.getClient_vat_id()
		    );
		}
		return result;
	}
}
