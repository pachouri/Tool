package com.kinkars.sync.info;

import java.io.IOException;

import com.kinkars.client.rest.RestOutputBuilder;

public class ExtportToOnlineDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SyncInvoiceInfoOut sys= new SyncInvoiceInfoOut();
		//RestOutputBuilder rob= new RestOutputBuilder();
		sys.getSyncClientInfo();
		sys.getSyncFamiliesInfo();
		sys.getSyncTaxRate();
        sys.getSyncUnits();
		sys.getSyncProductInfo();
		
		
		
	}

}
