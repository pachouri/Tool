package com.kinkars.sync.info.sqlquery;


public class InvoiceSQLQuery {

	public static String getMasterAddressInfo(String dbinfo){

		String query="select MasterAddressInfo.MasterCode, Master1.Name,MasterAddressInfo.Address1, MasterAddressInfo.Address2,"+
				"MasterAddressInfo.Address3,MasterAddressInfo.Address4,MasterAddressInfo.TelNo,MasterAddressInfo.FAX,MasterAddressInfo.EMAIL,"+
				"MasterAddressInfo.Mobile,MasterAddressInfo.GSTNo "+
				" from  "+dbinfo+".MasterAddressInfo  INNER JOIN  "+dbinfo+".Master1 ON MasterAddressInfo.MasterCode=Master1.Code";
		
		
		System.out.println("query"+ query);
		return query;
	}
}
