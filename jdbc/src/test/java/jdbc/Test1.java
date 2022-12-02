package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {
	
	private static String connectionString = "jdbc:sqlserver://D-ORDOX-VMM-C02.sedgwickcms.com\\ctabs2; " 
										+ " User ID = SCMSNA\\B.Gurbansahedov; "
										+ " MultipleActiveResultSets=False; "
										+ " Encrypt=False; "
										+ " TrustServerCertificate=False; "
										+ " integratedSecurity=true; "
										+ " Connection Timeout=45 ";
	
		public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
		Connection db = DriverManager.getConnection(connectionString);
		if (db != null) {
		    System.out.println("Connected");
		}else {
			System.out.println("Not connected");
		}
		
		Statement stmt=db.createStatement();  
		ResultSet rs=stmt.executeQuery("select top 10 * from [CTABS2].[dbo].[CLAIM]");
		ResultSetMetaData rsMD=rs.getMetaData();
		int columnCount = rsMD.getColumnCount();
//	    for (int i=1; i<=columnCount; i++) 
//	    {
//	        String columnName = rsMD.getColumnName(i);
//	        System.out.print(columnName+" | ");
//	    }
//	    	String values = rs.getString(1);
//	    	System.out.print(values +" | ");
//	        
	        
		spitOutAllTableRows("[CTABS2].[dbo].[CLAIM]", db);
		
		db.close();
		
		
  		
  		
	}
	 public static void spitOutAllTableRows(String tableName, Connection conn) {
		    try {
		      System.out.println("current " + tableName + " is:");
		      try (PreparedStatement selectStmt = conn.prepareStatement(
		              "SELECT top 15 * from " + tableName, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           ResultSet rs = selectStmt.executeQuery()) {
		        if (!rs.isBeforeFirst()) {
		          System.out.println("no rows found");
		        }
		        else {
		          System.out.println("types:");
		          for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
		            System.out.print(rs.getMetaData().getColumnName(i + 1) + ":" + rs.getMetaData().getColumnTypeName(i + 1) + " ");
		          }
		          System.out.println();
		          while (rs.next()) {
		            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
		              //System.out.print(" " + rs.getMetaData().getColumnName(i) + "=" + rs.getObject(i));
		              System.out.print(" | " + rs.getObject(i));
		            }
		            System.out.println("");
		          }
		        }
		      }
		    }
		    catch (SQLException e) {
		      throw new RuntimeException(e);
		    }
		  }
	
	

	
}
