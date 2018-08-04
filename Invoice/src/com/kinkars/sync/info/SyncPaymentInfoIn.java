package com.kinkars.sync.info;
import com.kinkars.database.sqlserver.GetPropertyValues;
import com.kinkars.sync.info.bean.PaymentInfo;
import com.kinkars.util.CommonUtility;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class SyncPaymentInfoIn
{
  public SyncPaymentInfoIn() {}
  
  static final Logger logger = Logger.getLogger(SyncPaymentInfoIn.class);
  
  public boolean addPayment() { boolean result = false;
    CommonUtility comm = new CommonUtility();
    GetPropertyValues prop = new GetPropertyValues();
    com.kinkars.json.reader.JSONReaderHandler jsonreaderhandler = new com.kinkars.json.reader.JSONReaderHandler();
    int nextinvoice = comm.getNextRecordToBeMigrate(2);
    try {
      PaymentInfo paymentinfo = jsonreaderhandler.getPayment(Integer.toString(nextinvoice));
      if (paymentinfo != null) {
        paymentinfo.getPayment_id();
        int vchcode = comm.getNexValue(prop.getPropValues().getProperty("Database"), "tran1", "vchcode");
        String vchno = comm.getVCHNOPadded(prop.getPropValues().getProperty("PAYMENTNAMEPREFIX") + "-" + paymentinfo.getPayment_id() + "-" + comm.getNexValue(prop.getPropValues().getProperty("Database"), "tran1", "vchcode"));
        int autovchno = comm.getNexValue(prop.getPropValues().getProperty("Database"), "tran1", "AUTOVCHNO");
        insertPaymentInfoHeader(paymentinfo.getPayment_amount(), vchcode, vchno, autovchno);
        insertPaymentInfoDetailOne(comm.getClientMasterCode(paymentinfo.getInvoice_id()), paymentinfo.getPayment_amount(), vchcode, vchno, autovchno, 1);
        insertPaymentInfoDetailTwo(1, paymentinfo.getPayment_amount(), vchcode, vchno, autovchno, 2);
        result = true;
      } else {
        result = false;
      }
    } catch (IOException e) {
      logger.error(e.getMessage());
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
    return result;
  }
  
  public static void insertPaymentInfoHeader(double amount, int vchcode, String vchno, int autovchno)
    throws SQLException
  {
    GetPropertyValues prop = new GetPropertyValues();
    Connection conn = null;
    conn = new com.kinkars.database.sqlserver.ConnectMSSQLServer().dbConnect();
    PreparedStatement preparedStatement = null;
    try {
      java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
      String insertTableSQL = com.kinkars.sync.info.sqlquery.InvoiceSQLQuery.insertPaymentInfoHeader(prop.getPropValues().getProperty("Database"));
      preparedStatement = conn.prepareStatement(insertTableSQL);
      preparedStatement.setInt(1, vchcode);
      preparedStatement.setInt(2, 14);
      preparedStatement.setTimestamp(3, timestamp);
      preparedStatement.setTimestamp(4, timestamp);
      preparedStatement.setString(5, vchno);
      preparedStatement.setInt(6, Integer.parseInt(prop.getPropValues().getProperty("PAYMENTVCHSERIES")));
      preparedStatement.setInt(7, 0);
      preparedStatement.setInt(8, 0);
      preparedStatement.setInt(9, 2);
      preparedStatement.setInt(10, autovchno);
      preparedStatement.setDouble(11, amount);
      preparedStatement.setDouble(12, amount);
      preparedStatement.executeUpdate();
      logger.info("Record is inserted into DBUSER table!");

    }
    catch (IOException e)
    {
      logger.error(e.getMessage());
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    finally
    {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
      
      if (conn != null) {
        conn.close();
      }
    }
  }
  


  public static void insertPaymentInfoDetailOne(int mastercode1, double amount, int vchcode, String vchno, int autovchno, int serialnubmer)
    throws SQLException
  {
    GetPropertyValues prop = new GetPropertyValues();
    Connection conn = null;
    conn = new com.kinkars.database.sqlserver.ConnectMSSQLServer().dbConnect();
    PreparedStatement preparedStatement = null;
    try {
      java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
      String insertTableSQL = com.kinkars.sync.info.sqlquery.InvoiceSQLQuery.insertPaymentInfoDetail(prop.getPropValues().getProperty("Database"));
      preparedStatement = conn.prepareStatement(insertTableSQL);
      preparedStatement.setInt(1, 1);
      preparedStatement.setInt(2, vchcode);
      preparedStatement.setInt(3, mastercode1);
      preparedStatement.setInt(4, 0);
      preparedStatement.setInt(5, serialnubmer);
      preparedStatement.setInt(6, 14);
      preparedStatement.setString(7, vchno);
      preparedStatement.setInt(8, Integer.parseInt(prop.getPropValues().getProperty("PAYMENTVCHSERIES")));
      preparedStatement.setDouble(9, -amount);
      preparedStatement.setTimestamp(10, timestamp);
      preparedStatement.setDouble(11, amount);
      
      preparedStatement.executeUpdate();
      logger.info("Record is inserted into DBUSER table!");

    }
    catch (IOException e)
    {
      logger.error(e.getMessage());
    } catch (SQLException e) {
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
  
  public static void insertPaymentInfoDetailTwo(int mastercode1, double amount, int vchcode, String vchno, int autovchno, int serialnubmer) throws SQLException {
    GetPropertyValues prop = new GetPropertyValues();
    Connection conn = null;
    conn = new com.kinkars.database.sqlserver.ConnectMSSQLServer().dbConnect();
    PreparedStatement preparedStatement = null;
    try {
      java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
      String insertTableSQL = com.kinkars.sync.info.sqlquery.InvoiceSQLQuery.insertPaymentInfoDetail(prop.getPropValues().getProperty("Database"));
      preparedStatement = conn.prepareStatement(insertTableSQL);
      preparedStatement.setInt(1, 1);
      preparedStatement.setInt(2, vchcode);
      preparedStatement.setInt(3, mastercode1);
      preparedStatement.setInt(4, 0);
      preparedStatement.setInt(5, serialnubmer);
      preparedStatement.setInt(6, 14);
      preparedStatement.setString(7, vchno);
      preparedStatement.setInt(8, Integer.parseInt(prop.getPropValues().getProperty("PAYMENTVCHSERIES")));
      preparedStatement.setDouble(9, amount);
      preparedStatement.setTimestamp(10, timestamp);
      preparedStatement.setDouble(11, 0.0D);
      preparedStatement.executeUpdate();
      logger.info("Record is inserted into DBUSER table!");
    }
    catch (IOException e)
    {
      logger.error(e.getMessage());
    } catch (SQLException e) {
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
}