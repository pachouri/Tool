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
	
	
	public static String getTaxRates(String dbinfo){

		String query="select Code, Name from "+dbinfo+".MASTER1 where MasterType=25 ";
	
		logger.info("query"+ query);
		return query;
	}
	
	public static String getUnitTypes(String dbinfo){

		String query="select Code, Name from "+dbinfo+".MASTER1 where MasterType=8 ";

		logger.info("query"+ query);
		return query;
	}
	
	public static String getProductInfo(String dbinfo){

		String query="SELECT Code ,MasterType ,Name ,PrintName ,ParentGrp  ,CM1, CM2,CM3,CM4  ,CM5  ,CM8  ,D1 ,D2   ,D3,D4 ,D5  ,D6 FROM "+dbinfo+".Master1 where MasterType=6";
		logger.info("query"+ query);
		return query;
	}
	
	public static String insertInvoiceInfoHeader(String dbinfo){
		String query="Insert into "+dbinfo+".TRAN1 (VchCode,VchType,Date,StockUpdationDate,Vchno,VchSeriesCode,MASTERCODE1,MASTERCODE2,STAMP,AUTOVCHNO,CM1,VCHAMTBASECUR,VCHSALEPURCAMT,ORGVCHAMTBASECUR,INPUTTYPE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		logger.info("query"+ query);
		return query;	
	}
	
	public static String insertInvoiceInfoCompany(String dbinfo){
		String query="Insert into "+dbinfo+".TRAN2 (RecType,Vchcode,Mastercode1,Mastercode2,SRNO,VCHTYPE,VCHNO,VCHSERIESCODE,VALUE1,date) VALUES (?,?,?,?,?,?,?,?,?,?)";
		logger.info("query"+ query);
		return query;	
	}
	
	public static String insertInvoiceInfoItemInfo(String dbinfo){
		String query="Insert into "+dbinfo+".TRAN2 (RecType,Vchcode,Mastercode1,Mastercode2,SRNO,VCHTYPE,VCHNO,VCHSERIESCODE,VALUE1,value2,value3,D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,CM1,CM2,CM3,CM4,CM5,CM6,CM7,date)"
				+" VALUES "
				+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		logger.info("query"+ query);
		return query;	
	}
	public static String getNextNubmer(String dbinfo,String fieldname){
		String query="select MAX("+fieldname+")+1 from  "+dbinfo;
		logger.info("query"+ query);
		return query;
		
	}
	public static String insertInvoiceInfoTax(String dbinfo){
		String query="Insert into "+dbinfo+".TRAN2 (RecType,Vchcode,Mastercode1,Mastercode2,SRNO,VCHTYPE,date,VCHNO,VCHSERIESCODE,VALUE1,VALUE2,VALUE3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		logger.info("query"+ query);
		return query;	
	}
	
}
