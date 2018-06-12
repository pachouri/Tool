package com.kinkars.sync.info;

public class InvoiceAndPaymentImport {

	public static void main(String[] args) {
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
				sys.getSyncFamiliesInfo();
				sys.getSyncTaxRate();
		        sys.getSyncUnits();
				sys.getSyncProductInfo();
			 
		 }else{
			 
			System.out.println("Please contact to admin, something   wrong");
		 }
		 
	
	}
}
