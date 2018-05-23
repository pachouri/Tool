package com.kinkars.sync.info;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.kinkars.client.rest.RestInputBuilder;
import com.kinkars.database.sqlserver.ConnectMSSQLServer;
import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.sync.info.sqlquery.InvoiceSQLQuery;
import com.kinkars.sync.info.bean.ClientInfo;
import com.kinkars.sync.info.bean.FamilyInfo;
import com.kinkars.sync.info.bean.ProductInfo;
import com.kinkars.sync.info.bean.TaxRateInfo;
import com.kinkars.sync.info.bean.UnitInfo;

import org.apache.log4j.Logger;

public class SyncInvoiceInfo {
	final static Logger logger = Logger.getLogger(SyncInvoiceInfo.class);

	public void getSyncClientInfo(){
		GetPropertyValues prop = new GetPropertyValues();
		List<ClientInfo> ci=new ArrayList<ClientInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getMasterAddressInfo(prop.getPropValues().getProperty("Database"));
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				ClientInfo clientInfo=new ClientInfo();;
				clientInfo.setExt_client_id(rs.getInt("MasterCode"));
				clientInfo.setClient_name(rs.getString("Name"));
				clientInfo.setClient_address_1(rs.getString("Address1"));
				clientInfo.setClient_address_2(rs.getString("Address2")+","+ rs.getString("Address3")+","+ rs.getString("Address4"));
				clientInfo.setClient_phone(rs.getString("TelNo"));
				clientInfo.setClient_fax(rs.getString("FAX"));
				clientInfo.setClient_email(rs.getString("EMAIL"));
				clientInfo.setClient_mobile(rs.getString("Mobile"));
				clientInfo.setClient_vat_id(rs.getString("GSTNo"));
				ci.add(clientInfo);
			} 
			RestInputBuilder rib=new RestInputBuilder();
			rib.syncClients(ci);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void getSyncFamiliesInfo(){
		GetPropertyValues prop = new GetPropertyValues();
		List<FamilyInfo> fi=new ArrayList<FamilyInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getParentGroup(prop.getPropValues().getProperty("Database"));
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				FamilyInfo familyInfo=new FamilyInfo();
				familyInfo.setExt_family_id(rs.getInt("Code"));
				familyInfo.setFamily_name(rs.getString("Name"));
				fi.add(familyInfo);
			} 
			RestInputBuilder rib=new RestInputBuilder();
			rib.syncFamilies(fi);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	public void getSyncTaxRate(){
		GetPropertyValues prop = new GetPropertyValues();
		List<TaxRateInfo> tri=new ArrayList<TaxRateInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getTaxRates(prop.getPropValues().getProperty("Database"));
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				TaxRateInfo taxRateInfo=new TaxRateInfo();
				taxRateInfo.setExt_tax_id(rs.getInt("Code"));
				taxRateInfo.setTax_rate_name(rs.getString("Name"));
				taxRateInfo.setTax_rate_percent(5.00);
				tri.add(taxRateInfo);
			} 
			RestInputBuilder rib=new RestInputBuilder();
			rib.syncTaxRate(tri);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	public void getSyncProductInfo(){
		GetPropertyValues prop = new GetPropertyValues();
		List<ProductInfo> pi=new ArrayList<ProductInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getProductInfo(prop.getPropValues().getProperty("Database"));
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				ProductInfo productInfo=new ProductInfo();
				productInfo.setExt_product_id(rs.getInt("code"));//Code
				productInfo.setProduct_name(rs.getString("Name"));//Name
				productInfo.setProduct_description(rs.getString("Name"));//Description
				productInfo.setUnit_id(rs.getString("CM1"));//UnitType
				productInfo.setPurchase_price(rs.getString("D4"));//Purchase Price
				productInfo.setProduct_price(rs.getString("D3"));//Sale Price
				productInfo.setTax_rate_id(rs.getString("CM8"));//tax Rate
				productInfo.setProduct_family_id(rs.getString("ParentGrp"));//Family
				pi.add(productInfo);
			} 
			RestInputBuilder rib=new RestInputBuilder();
			rib.syncProducts(pi);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	public void getSyncUnits(){
		GetPropertyValues prop = new GetPropertyValues();
		List<UnitInfo> ui=new ArrayList<UnitInfo>();
		Connection conn=null;
		conn= new ConnectMSSQLServer().dbConnect();
		Statement statement=null;
		try {
			statement = conn.createStatement(); 
			String queryString = InvoiceSQLQuery.getUnitTypes(prop.getPropValues().getProperty("Database"));
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {
				UnitInfo  unitinfo=new UnitInfo();
				unitinfo.setExt_unit_id(rs.getInt("Code"));
				unitinfo.setUnit_name(rs.getString("Name"));
				
				ui.add(unitinfo);
			} 
			RestInputBuilder rib=new RestInputBuilder();
			rib.syncUnitType(ui);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}