package com.kinkars.sync.info;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.kinkars.json.reader.JSONReaderHandler;

public class InvoiceAndPaymentImport {
	final static Logger logger = Logger.getLogger(InvoiceAndPaymentImport.class);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if(args.length == 0){

		}else if(args[0].equals("IP")){
			SyncInvoiceInfoIn sys= new SyncInvoiceInfoIn();
			SyncPaymentInfoIn sps=new SyncPaymentInfoIn();

			while(true)
			{
				boolean result =sys.addInvoice();
				if(!result)
				{
					System.out.println("No More Invoice");
					break;

				}
			}

			while(true)
			{
				boolean result =sps.addPayment();
				if(!result)
				{   
					System.out.println("No More Payment");
					break;
				}
			}

		}
		else if(args[0].equals("SNYC")){
			SyncInvoiceInfoOut sys= new SyncInvoiceInfoOut();
			sys.getSyncClientInfo();
			//sys.getSyncFamiliesInfo();
			sys.getSyncTaxRate();
			sys.getSyncUnits();
			sys.getSyncProductInfo();

		}
		else if(args[0].equals("ECOM")){
			SyncInvoiceInfoOut sys= new SyncInvoiceInfoOut();
			//sys.getSyncFamiliesInfo("OF1",64);
			//sys.getSyncFamiliesInfo("OF3",2);
			// sys.updateSubCategory("OF1",1);
			//sys.updateSubCategory("OF2",2);
			//sys.getSyncProductInfo();
			//sys.updateSubCategory();
			//sys.updateProductPriceInfo();
			sys.deleteNewArrivalCategory();
			//sys.updateNewArrival();
			logger.error("Test");
		}else{

			System.out.println("Please contact to admin, something   wrong");
		}


	}
}
