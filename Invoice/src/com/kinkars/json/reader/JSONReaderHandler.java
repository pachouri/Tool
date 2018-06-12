package com.kinkars.json.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.kinkars.client.rest.RestOutputBuilder;
import com.kinkars.sync.info.SyncInvoiceInfoOut;
import com.kinkars.sync.info.bean.FamilyInfo;
import com.kinkars.sync.info.bean.InvoiceInfo;
import com.kinkars.sync.info.bean.ItemInfo;
import com.kinkars.sync.info.bean.PaymentInfo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReaderHandler {
	final static Logger logger = Logger.getLogger(JSONReaderHandler.class);
	public InvoiceInfo getInvoice(String id){
		InvoiceInfo invoiceinfo = new InvoiceInfo();
		RestOutputBuilder rob=new RestOutputBuilder();
		JSONParser parser = new JSONParser();
		try {
			rob.syncInvoice(id);
			Object obj = parser.parse(rob.syncInvoice(id));
			JSONObject jsonObject = (JSONObject) obj;
			InvoiceInfo invinf=new  InvoiceInfo();
			invinf.setInvoice_date_created((String) jsonObject.get("invoice_date_created"));
			invinf.setInvoice_total( Double.parseDouble(jsonObject.get("invoice_total").toString())); 
			invinf.setInvoice_time_created((String) jsonObject.get("invoice_time_created"));
			invinf.setUser_id(Integer.parseInt(jsonObject.get("user_id").toString()));
			invinf.setInvoice_item_subtotal(Double.parseDouble(jsonObject.get("invoice_item_subtotal").toString()));
			invinf.setInvoice_id(Integer.parseInt(jsonObject.get("invoice_id").toString()));
			invinf.setExt_client_id(Integer.parseInt( jsonObject.get("ext_client_id").toString()));
			invinf.setInvoice_item_tax_total(Double.parseDouble(jsonObject.get("invoice_item_tax_total").toString()));
			invinf.setInvoice_number(Integer.parseInt(jsonObject.get("invoice_number").toString()));
			JSONArray items = (JSONArray) jsonObject.get("items");
			List<ItemInfo> iteminfo=new ArrayList<ItemInfo>();
			for (int i=0; i<items.size(); i++){
				Object objInternal = parser.parse(items.get(i).toString());
				JSONObject jsonObjectInternal = (JSONObject) objInternal;
				ItemInfo ii=new  ItemInfo();
				System.out.println(jsonObjectInternal.get("ext_product_id").toString());
				ii.setExt_product_id(Integer.parseInt( jsonObjectInternal.get("ext_product_id").toString()));
				ii.setExt_tax_id(Integer.parseInt( jsonObjectInternal.get("ext_tax_id").toString()));
				if(null == (jsonObjectInternal.get("item_discount_amount"))){
					ii.setItem_discount_amount(0.00);
				}else{
					ii.setItem_discount_amount(Double.parseDouble(jsonObjectInternal.get("item_discount_amount").toString()));
				}
				ii.setItem_price( Double.parseDouble(jsonObjectInternal.get("item_price").toString()));
				ii.setItem_quantity(Double.parseDouble( jsonObjectInternal.get("item_quantity").toString()));
				ii.setExt_unit_id(Integer.parseInt(jsonObjectInternal.get("ext_unit_id").toString()));
				ii.setItem_subtotal(Double.parseDouble(jsonObjectInternal.get("item_subtotal").toString()));
				ii.setItem_tax_total(Double.parseDouble(jsonObjectInternal.get("item_tax_total").toString()));
				ii.setItem_total(Double.parseDouble(jsonObjectInternal.get("item_total").toString()));
				ii.setTax_rate_percent(Double.parseDouble(jsonObjectInternal.get("tax_rate_percent").toString()));
				iteminfo.add(ii);
			}
			invinf.setItems(iteminfo);
			invoiceinfo=invinf;

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			invoiceinfo=null;
			logger.error(e.getMessage());
		}
		return invoiceinfo;
	}

	public PaymentInfo getPayment(String paymentid) throws IOException{

		PaymentInfo paymentinfo = new PaymentInfo();
		RestOutputBuilder rob=new RestOutputBuilder();
		JSONParser jsonParser = new JSONParser();
		try {
			Object obj = jsonParser.parse(rob.syncPayment(paymentid));
			JSONArray pi= (JSONArray) obj;
			System.out.println(pi.size());
			if( pi.size() <= 0){
			paymentinfo = null;
			}else{
			JSONObject piObject = (JSONObject) pi.get(0);
			int  payment_id = Integer.parseInt(piObject.get("payment_id").toString());  
			int  invoice_id =  Integer.parseInt(piObject.get("invoice_id").toString());
			String payment_date= piObject.get("payment_date").toString();
			double payment_amount=Double.parseDouble(piObject.get("payment_amount").toString());
			int invoice_number=Integer.parseInt(piObject.get("invoice_number").toString());
			paymentinfo.setInvoice_id(invoice_id);
			paymentinfo.setInvoice_number(invoice_number);
			paymentinfo.setPayment_amount(payment_amount);
			paymentinfo.setPayment_date(payment_date);
			paymentinfo.setPayment_id(payment_id);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			paymentinfo= null;
		}		
		return paymentinfo;
	}
	 public static void main(String [] args) throws IOException{
		 JSONReaderHandler jrh=new JSONReaderHandler();
		 jrh.getPayment("9");
		 
	 }
}
