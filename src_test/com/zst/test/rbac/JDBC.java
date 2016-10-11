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
	//STEP 2: Register JDBC driver,ע��һ��������������õķ�����ʹ��Java��Class.forName()��������̬����������������ļ����ڴ��У�
		   //�����Զ�����ע�ᡣ���ַ����ǿ�ȡ�ģ���Ϊ������ʹ����ע�����ã�
	      Class.forName(JDBC_DRIVER).newInstance();
	     
	      //DriverManager.registerDriver(driver);//�ڶ���ע�������ķ���
	//STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      conn.setAutoCommit(false);
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      			//(1) ͨ��statment���� 
	     // stmt=conn.createStatement();
	      
	      stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);//������ɾ����resultSet���ϵ�statement����**����ѯ�ı������������
	      rs = stmt.executeQuery(sql);
	      conn.commit();
	      			//(2) ͨ��preparedstatment���� 
	      
	    /*  sql = "SELECT  * FROM attend_duty WHERE REGISTER_TIME>'2016-07-21' and USER_ID=?";
	      pstmt=conn.prepareStatement(sql);
	      pstmt.setInt(1, 149);//��̬��ֵ
	      rs= pstmt.executeQuery();*/
	      			//(3) ͨ��callablestatment���� 
	     /* String SQL = "{call getEmpName (?, ?)}";
	      cstmt = conn.prepareCall (SQL);
	      pstmt.setInt(1, 35);//��̬��ֵ
	      cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);//�����������ݣ�����Ҫ����ע��
	      cstmt.executeQuery();
	      cstmt.getString(2);//ͨ�����ַ�ʽ��ȡ�����ֵ��
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


