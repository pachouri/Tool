package com.kinkars.sync.info.sqlquery;

import org.apache.log4j.Logger;


public class InvoiceSQLQuery {
	final static Logger logger = Logger.getLogger(InvoiceSQLQuery.class);
	public static String getMasterAddressInfo(String dbinfo){

		String query="select MasterAddressInfo.MasterCode, Master1.Name,MasterAddressInfo.Address1, MasterAddressInfo.Address2,"+
				"MasterAddressInfo.Address3,MasterAddressInfo.Address4,MasterAddressInfo.TelNo,MasterAddressInfo.FAX,MasterAddressInfo.EMAIL,"+
				"MasterAddressInfo.Mobile,MasterAddressInfo.GSTNo "+
				" from  "+dbinfo+".MasterAddressInfo  INNER JOIN  "+dbinfo+".Master1 ON MasterAddressInfo.MasterCode=Master1.Code";
		
		logger.info("query"+ query);
		return query;
	}
	
	public static String getParentGroup(String dbinfo){

		String query="select Code, Name from "+dbinfo+".MASTER1 where MasterType=5 ";
		
		logger.info("query"+ query);
		return query;
	}
}
