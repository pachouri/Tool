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
import com.kinkars.sync.info.SyncInvoiceInfoOut;
import com.kinkars.sync.info.bean.ClientInfo;
import com.kinkars.sync.info.bean.FamilyInfo;
import com.kinkars.sync.info.bean.ProductInfo;
import com.kinkars.sync.info.bean.TaxRateInfo;
import com.kinkars.sync.info.bean.UnitInfo;


public class RestInputBuilder {
	final static Logger logger = Logger.getLogger(SyncInvoiceInfoOut.class);
	public String syncClients(List<ClientInfo> clientInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"clients";
		for(int i=0; i<clientInfo.size(); i++){
			ClientInfo ci=clientInfo.get(i);
			
			logger.info("Param"+ "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name());
			System.out.println("Test is going on"+"ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_client_id="+ci.getExt_client_id()+"&client_name="+ci.getClient_name()+"&client_address_1="+ci.getClient_address_1()
			+"&client_address_2="+ci.getClient_address_2()+"&client_phone="+ci.getClient_phone()
			+"&client_fax="+ci.getClient_fax()+"&client_email="+ci.getClient_mobile()+
			"&client_vat_id="+ci.getClient_vat_id()
		    );
		}
		System.out.println("Test Result"+result);
		return result;
	}
	public String syncFamilies(List<FamilyInfo> familyInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"categories";
		for(int i=0; i<familyInfo.size(); i++){
			FamilyInfo fi=familyInfo.get(i);
			logger.info("Param"+ "parent_id="+fi.getExt_family_id()+"&childCategoryName="+fi.getFamily_name());
			result=HttpURLConnectionRest.sendPOST(url, "parent_id="+fi.getExt_family_id()+"&childCategoryName="+fi.getFamily_name());
		}
		return result;
	}
	
	public String syncTaxRate(List<TaxRateInfo> taxRateInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"taxrates";
		for(int i=0; i<taxRateInfo.size(); i++){
			TaxRateInfo ti=taxRateInfo.get(i);
			logger.info("Param"+ "ext_tax_id="+ti.getExt_tax_id()+"&tax_rate_name="+ti.getTax_rate_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_tax_id="+ti.getExt_tax_id()+"&tax_rate_name="+ti.getTax_rate_name()+"&tax_rate_percent="+ti.getTax_rate_percent());
		}
		return result;
	}
	
	public String syncUnitType(List<UnitInfo> unitinfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"units";
		for(int i=0; i<unitinfo.size(); i++){
			UnitInfo ui=unitinfo.get(i);
			logger.info("Param"+ "ext_unit_id="+ui.getExt_unit_id()+"&unit_name="+ui.getUnit_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_unit_id="+ui.getExt_unit_id()+"&unit_name="+ui.getUnit_name());
		}
		return result;
	}
	
	
	public String syncProducts(List<ProductInfo> productInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"products";
		for(int i=0; i<productInfo.size(); i++){
			ProductInfo pi=productInfo.get(i);
			
			logger.info("Param"+ "ext_product_id="+pi.getExt_product_id()+"&product_name="+pi.getProduct_name());
			
			result=HttpURLConnectionRest.sendPOST(url, "ext_product_id="+pi.getExt_product_id()+"&product_name="+pi.getProduct_name()+"&product_description="+pi.getProduct_description()
			+"&product_price="+pi.getProduct_price()+"&purchase_price="+pi.getProduct_price()
			+"&tax_rate_id="+pi.getTax_rate_id()+"&unit_id="+pi.getUnit_id()+
			"&family_id="+pi.getProduct_family_id()
		    );
		}
		return result;
	}
	
	public String updatePrice(List<ProductInfo> productInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"price";
		for(int i=0; i<productInfo.size(); i++){
			ProductInfo pi=productInfo.get(i);
			
			logger.info("Param"+ "ext_product_id="+pi.getExt_product_id()+"&product_name="+pi.getProduct_name());
			
			result=HttpURLConnectionRest.sendPOST(url, "ext_product_id="+pi.getExt_product_id()+"&product_name="+pi.getProduct_name()+"&product_description="+pi.getProduct_description()
			+"&product_price="+pi.getProduct_price()+"&purchase_price="+pi.getProduct_price()
			+"&tax_rate_id="+pi.getTax_rate_id()+"&unit_id="+pi.getUnit_id()+
			"&family_id="+pi.getProduct_family_id()
		    );
		}
		return result;
	}
	
	
	public String updateNewArrival(List<ProductInfo> productInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"newarrival";
		for(int i=0; i<productInfo.size(); i++){
			ProductInfo pi=productInfo.get(i);
			
			logger.info("Param"+ "ext_product_id="+pi.getExt_product_id()+"&product_name="+pi.getProduct_name());
			
			result=HttpURLConnectionRest.sendPOST(url, "ext_product_id="+pi.getExt_product_id()+"&product_name="+pi.getProduct_name()+"&product_description="+pi.getProduct_description()
			+"&product_price="+pi.getProduct_price()+"&purchase_price="+pi.getProduct_price()
			+"&tax_rate_id="+pi.getTax_rate_id()+"&unit_id="+pi.getUnit_id()+
			"&family_id="+pi.getProduct_family_id()
		    );
		}
		return result;
	}
	
	public String deleteNewArrival() throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"newarrivaldel";
			//logger.info("Param"+ "ext_product_id="+pi.getExt_product_id()+"&product_name="+pi.getProduct_name());
			result=HttpURLConnectionRest.sendPOST(url,"");
		return result;
	}
	public String updateSubCategory(List<FamilyInfo> familyInfo) throws IOException{
		String result =null;
		GetPropertyValues prop = new GetPropertyValues();
		String url=(prop.getPropValues().getProperty("BASE_URL"))+"subcategories";
		for(int i=0; i<familyInfo.size(); i++){
			FamilyInfo fi=familyInfo.get(i);
			logger.info("Param"+ "ext_product_id="+fi.getExt_family_id()+"&category_name="+fi.getFamily_name());
			result=HttpURLConnectionRest.sendPOST(url, "ext_product_id="+fi.getExt_family_id()+"&category_name="+fi.getFamily_name());
		}
		return result;
	}
}
