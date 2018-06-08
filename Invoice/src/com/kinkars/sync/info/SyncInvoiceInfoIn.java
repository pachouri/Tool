package com.kinkars.sync.info;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import com.kinkars.database.sqlserver.ConnectMSSQLServer;
import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.json.reader.JSONReaderHandler;
import com.kinkars.sync.info.bean.InvoiceInfo;
import com.kinkars.sync.info.bean.ItemInfo;
import com.kinkars.sync.info.sqlquery.InvoiceSQLQuery;
import com.kinkars.util.CommonUtility;

public class SyncInvoiceInfoIn {
	final static Logger logger = Logger.getLogger(SyncInvoiceInfoIn.class);
	public static void addInvoice(){
		int serialnumberRec=1;
		GetPropertyValues prop = new GetPropertyValues();
		JSONReaderHandler jsonreaderhandler= new JSONReaderHandler();
		InvoiceInfo invoiceInfo=jsonreaderhandler.getInvoice("1");
		CommonUtility comm=new CommonUtility();
		invoiceInfo.getExt_client_id();
		try {
			int vchcode=comm.getNexValue(prop.getPropValues().getProperty("Database"), "tran1", "vchcode");
			String vchno =comm.getVCHNOPadded("IP"+comm.getNexValue(prop.getPropValues().getProperty("Database"), "tran1", "vchcode"));
			int autovchno=comm.getNexValue(prop.getPropValues().getProperty("Database"), "tran1", "AUTOVCHNO");
			int companycode=invoiceInfo.getExt_client_id();
			insertInvoiceInfoHeader(invoiceInfo.getExt_client_id(), 201,invoiceInfo.getInvoice_total(), vchcode,vchno,autovchno);
			insertInvoiceInfoCompany(invoiceInfo.getExt_client_id(), 201,invoiceInfo.getInvoice_total(),vchcode,vchno,autovchno);
			insertInvoiceInfoSale(invoiceInfo.getExt_client_id(), 201,invoiceInfo.getInvoice_total(),vchcode,vchno,autovchno);
			List<ItemInfo> iteminfo=invoiceInfo.getItems();
			for(int i=0; i<iteminfo.size(); i++){
				System.out.println(iteminfo.get(i).getExt_tax_id());
				insertInvoiceItems(iteminfo.get(i).getExt_product_id(), 201,iteminfo.get(i).getItem_price(),vchcode,vchno,autovchno,i,iteminfo.get(i).getItem_quantity(),iteminfo.get(i).getItem_price(),companycode,iteminfo.get(i).getExt_tax_id(),iteminfo.get(i).getExt_unit_id());
				// insertInvoiceInfoTax(int mastercode1, String vchno,int serialnumber,double taxrate, double totalvalue,double taxvalue )
				if(iteminfo.get(i).getTax_rate_percent() > 0){
				
					//CGST
					String CGST=prop.getPropValues().getProperty("CGST");
					String SGST=prop.getPropValues().getProperty("SGST");
				   insertInvoiceInfoTax(Integer.parseInt(CGST),vchno, serialnumberRec++, iteminfo.get(i).getTax_rate_percent()/2,  iteminfo.get(i).getItem_subtotal()/2, iteminfo.get(i).getItem_tax_total()/2 );
				    //SGST
				    insertInvoiceInfoTax(Integer.parseInt(SGST),vchno, serialnumberRec++, iteminfo.get(i).getTax_rate_percent()/2,  iteminfo.get(i).getItem_subtotal()/2, iteminfo.get(i).getItem_tax_total()/2 );
				}
			}
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	//Tran1 Insert
	public static void insertInvoiceInfoHeader(int mastercode1, int mastercode2,double amount, int vchcode,String vchno,int autovchno) throws SQLException {
		GetPropertyValues prop = new GetPropertyValues();
		Connection conn=null;
		CommonUtility comm=new CommonUtility();
		conn= new ConnectMSSQLServer().dbConnect();
		PreparedStatement preparedStatement = null;
		try {
			Calendar cal=null;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String insertTableSQL = InvoiceSQLQuery.insertInvoiceInfoHeader(prop.getPropValues().getProperty("Database"));
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, vchcode);
			preparedStatement.setInt(2, 9);//Sale Type Always 9
			preparedStatement.setTimestamp(3, timestamp);
			preparedStatement.setTimestamp(4, timestamp);
			preparedStatement.setString(5, vchno);//VCHNO
			preparedStatement.setInt(6, Integer.parseInt(prop.getPropValues().getProperty("VCHSERIES")));//VchSeriesCode - This will come from config
			preparedStatement.setInt(7, mastercode1);//MasterCode1
			preparedStatement.setInt(8, mastercode2);//Mastercode2
			preparedStatement.setInt(9, 2);//STAMP
			preparedStatement.setInt(10, autovchno);//AUTOVCHNO
			preparedStatement.setInt(11, Integer.parseInt(prop.getPropValues().getProperty("TEXTYPE")));//CM1
			preparedStatement.setDouble(12, amount);//VCHAMTBASECUR
			preparedStatement.setDouble(13, amount);//VCHSALEPURCAMT
			preparedStatement.setDouble(14, amount);//ORGVCHAMTBASECUR
			preparedStatement.setInt(15, 1);//
			preparedStatement.executeUpdate();
			logger.info("Record is inserted into DBUSER table!");

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (conn != null) {
				conn.close();
			}

		}

	}


	//Tran2
	public static void insertInvoiceInfoCompany(int mastercode1, int mastercode2,double amount, int vchcode,String vchno,int autovchno) throws SQLException {
		GetPropertyValues prop = new GetPropertyValues();
		Connection conn=null;
		CommonUtility comm=new CommonUtility();
		conn= new ConnectMSSQLServer().dbConnect();
		PreparedStatement preparedStatement = null;
		try {
			Calendar cal=null;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String insertTableSQL = InvoiceSQLQuery.insertInvoiceInfoCompany(prop.getPropValues().getProperty("Database"));
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, 1);//RecType
			preparedStatement.setInt(2, vchcode);//vchcode
			preparedStatement.setInt(3, mastercode1);//mastercode1
			preparedStatement.setInt(4, mastercode2);//mastercode2
			preparedStatement.setInt(5, 1);//SRNIO
			preparedStatement.setInt(6, 9);//VHCTYPE
			preparedStatement.setString(7, vchno);//VCHNO
			preparedStatement.setInt(8, Integer.parseInt(prop.getPropValues().getProperty("VCHSERIES")));//VCHSERIES
			preparedStatement.setDouble(9, amount);//	
			preparedStatement.setTimestamp(10, timestamp);//
			preparedStatement.executeUpdate();
			logger.info("Record is inserted into DBUSER table!");

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	public static void insertInvoiceInfoSale(int mastercode1, int mastercode2,double amount, int vchcode,String vchno,int autovchno) throws SQLException {
		GetPropertyValues prop = new GetPropertyValues();
		Connection conn=null;
		CommonUtility comm=new CommonUtility();
		conn= new ConnectMSSQLServer().dbConnect();
		PreparedStatement preparedStatement = null;
		try {
			Calendar cal=null;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String insertTableSQL = InvoiceSQLQuery.insertInvoiceInfoCompany(prop.getPropValues().getProperty("Database"));
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, 1);//RecType
			preparedStatement.setInt(2, vchcode);//vchcode
			preparedStatement.setInt(3, 4);//mastercode1
			preparedStatement.setInt(4, 0);//mastercode2
			preparedStatement.setInt(5, 2);//SRNIO
			preparedStatement.setInt(6, 9);//VHCTYPE
			preparedStatement.setString(7, vchno);//VCHNO
			preparedStatement.setInt(8, Integer.parseInt(prop.getPropValues().getProperty("VCHSERIES")));//VCHSERIES
			preparedStatement.setDouble(9, amount);//
			preparedStatement.setTimestamp(10, timestamp);//
			preparedStatement.executeUpdate();
			logger.info("Record is inserted into DBUSER table!");

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public static void insertInvoiceItems(int mastercode1, int mastercode2,double amount, int vchcode,String vchno,int autovchno, int serialnumber,double quantity,double price, int companycode, int taxcode, int unit_id) throws SQLException {
		GetPropertyValues prop = new GetPropertyValues();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Connection conn=null;
		CommonUtility comm=new CommonUtility();
		conn= new ConnectMSSQLServer().dbConnect();
		PreparedStatement preparedStatement = null;
		try {
			Calendar cal=null;
			String insertTableSQL = InvoiceSQLQuery.insertInvoiceInfoItemInfo(prop.getPropValues().getProperty("Database"));
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, 2);//RecType
			preparedStatement.setInt(2, vchcode);//vchcode
			preparedStatement.setInt(3, mastercode1);//mastercode1
			preparedStatement.setInt(4, Integer.parseInt(prop.getPropValues().getProperty("STORE")));//mastercode2 config
			preparedStatement.setInt(5, serialnumber+1);//SRNIO
			preparedStatement.setInt(6, 9);//VHCTYPE
			preparedStatement.setString(7, vchno);//VCHNO
			preparedStatement.setInt(8, Integer.parseInt(prop.getPropValues().getProperty("VCHSERIES")));//VCHSERIES config
			preparedStatement.setDouble(9, -quantity);//Value1: Quantiy
			preparedStatement.setDouble(10, -quantity);//Value2: Quantiy
			preparedStatement.setDouble(11, -price*quantity);//Value3: Total 
			preparedStatement.setDouble(12, -quantity);//D1: Qunatity 
			preparedStatement.setDouble(13, price);//D2: Price per Unit
			preparedStatement.setInt(14, 0);//D3
			preparedStatement.setInt(15, 0);//D4: Unknown
			preparedStatement.setDouble(16, price*quantity);//D5: Total of All item
			preparedStatement.setDouble(17, price);//D6: Price Per Item
			preparedStatement.setInt(18, 0);//D7:0 
			preparedStatement.setInt(19, 1);//D8: 1 
			preparedStatement.setInt(20, 0);//D9: Unkonwn 
			preparedStatement.setInt(21, 0);//D10: unknown
			preparedStatement.setInt(22, 0);//D11: 0
			preparedStatement.setInt(23, companycode);//CM1: Company
			preparedStatement.setInt(24, unit_id);//CM2:unit
			preparedStatement.setInt(25, unit_id);//CM3: Unit
			preparedStatement.setInt(26, taxcode);//CM4:  Tax
			preparedStatement.setInt(27, 0);//CM5: 0
			preparedStatement.setInt(28, 0);//CM6: 0
			preparedStatement.setInt(29, 0);//CM7: 0
			preparedStatement.setTimestamp(30, timestamp);//Date
			preparedStatement.executeUpdate();
			logger.info("Record is inserted into DBUSER table!");

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	
	public static void insertInvoiceInfoTax(int mastercode1, String vchno,int serialnumber,double taxrate, double totalvalue,double taxvalue ) throws SQLException {
		GetPropertyValues prop = new GetPropertyValues();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Connection conn=null;
		CommonUtility comm=new CommonUtility();
		conn= new ConnectMSSQLServer().dbConnect();
		PreparedStatement preparedStatement = null;
		try {
			Calendar cal=null;
			String insertTableSQL = InvoiceSQLQuery.insertInvoiceInfoTax(prop.getPropValues().getProperty("Database"));
			preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, 3);//RecType
			preparedStatement.setInt(2, 0);//vchcode
			preparedStatement.setInt(3, mastercode1);//mastercode1-SGST-CGST
			preparedStatement.setInt(4, 0);//mastercode2
			preparedStatement.setInt(5, serialnumber);//SRNIO//Change// everyline
			preparedStatement.setInt(6, 9);//VHCTYPE
			preparedStatement.setTimestamp(7, timestamp);//date
			preparedStatement.setString(8, vchno);//VCHNO
			preparedStatement.setInt(9, Integer.parseInt(prop.getPropValues().getProperty("VCHSERIES")));//VCHSERIES--Config
			preparedStatement.setDouble(10, taxrate);//	value1 Tax Rate
			preparedStatement.setDouble(11, totalvalue);//	value2 Total value
			preparedStatement.setDouble(12, taxvalue);//	value3 Tax Value
			preparedStatement.executeUpdate();
			logger.info("Record is inserted into DBUSER table!");

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void main(String [] args){
		SyncInvoiceInfoIn.addInvoice();

	}


}
