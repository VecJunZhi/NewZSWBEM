package com.zst.test.rbac;
import java.sql.*;

public class JDBC {
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://192.168.1.197:3336/TD_OA";

	   //  Database credentials
	   static final String USER = "oa";
	   static final String PASS = "myoa888";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   PreparedStatement pstmt=null;
	   CallableStatement cstmt = null;
	   ResultSet rs=null;
	   String sql;
	   sql = "SELECT  * FROM address";
	   try{
	//STEP 2: Register JDBC driver,注册一个驱动程序中最常用的方法是使用Java的Class.forName()方法来动态加载驱动程序的类文件到内存中，
		   //它会自动将其注册。这种方法是可取的，因为它允许使驱动注册配置，
	      Class.forName(JDBC_DRIVER).newInstance();
	     
	      //DriverManager.registerDriver(driver);//第二种注册驱动的方法
	//STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      conn.setAutoCommit(false);
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      			//(1) 通过statment对象 
	     // stmt=conn.createStatement();
	      
	      stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);//创建可删除的resultSet集合的statement对象**所查询的表必须有主健。
	      rs = stmt.executeQuery(sql);
	      conn.commit();
	      			//(2) 通过preparedstatment对象 
	      
	    /*  sql = "SELECT  * FROM attend_duty WHERE REGISTER_TIME>'2016-07-21' and USER_ID=?";
	      pstmt=conn.prepareStatement(sql);
	      pstmt.setInt(1, 149);//动态赋值
	      rs= pstmt.executeQuery();*/
	      			//(3) 通过callablestatment对象 
	     /* String SQL = "{call getEmpName (?, ?)}";
	      cstmt = conn.prepareCall (SQL);
	      pstmt.setInt(1, 35);//动态赋值
	      cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);//如果是输出数据，则需要这样注册
	      cstmt.executeQuery();
	      cstmt.getString(2);//通过这种方式获取输出的值。
*/	      //STEP 5: Extract data from result set
	      while(rs.next()){
	        
	         String first = rs.getString("add_id");
	         if(rs.getRow()==2){
	        	 rs.deleteRow();
	        	 continue;
	         }
	         System.out.println("First: " + first+" row "+rs.getRow());
	         
	        
	      }
	      
	      //STEP 6: Clean-up environment
	      rs.close();
	      //stmt.close();
	      //conn.close();
	   }catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	      try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	   public static void  txManager(Connection conn){
		   try{
			   //Assume a valid connection object conn
			   conn.setAutoCommit(false);
			   Statement stmt = conn.createStatement();
			   
			   String SQL = "INSERT INTO Employees  " +
			                "VALUES (106, 20, 'Rita', 'Tez')";
			   stmt.executeUpdate(SQL);  
			   //Submit a malformed SQL statement that breaks
			   String SQL2 = "INSERTED IN Employees  " +
			                "VALUES (107, 22, 'Sita', 'Singh')";
			   stmt.executeUpdate(SQL2);
			   // If there is no error.
			   conn.commit();
			}catch(SQLException se){
			   // If there is any error.
			   try {
				conn.rollback();
			   } catch (SQLException e) {
				   e.printStackTrace();
			   }
			}
	   }
	   public static void batchManager(Connection conn){
		// Create SQL statement
		   String SQL = "INSERT INTO Employees (id, first, last, age) " +
		                "VALUES(?, ?, ?, ?)";

		   // Create PrepareStatement object
		    PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(SQL);
			//Set auto-commit to false
			   conn.setAutoCommit(false);

			   // Set the variables
			   pstmt.setInt( 1, 400 );
			   pstmt.setString( 2, "Pappu" );
			   pstmt.setString( 3, "Singh" );
			   pstmt.setInt( 4, 33 );
			   // Add it to the batch
			   pstmt.addBatch();

			   // Set the variables
			   pstmt.setInt( 1, 401 );
			   pstmt.setString( 2, "Pawan" );
			   pstmt.setString( 3, "Singh" );
			   pstmt.setInt( 4, 31 );
			   // Add it to the batch
			   pstmt.addBatch();

			   //add more batches
			   //Create an int[] to hold returned values
			   int[] count = pstmt.executeBatch();
			   //Explicitly commit statements to apply changes
			   conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   
	   }
}//end FirstExample


