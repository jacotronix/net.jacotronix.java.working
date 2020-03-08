import java.sql.*;

public class MySQLUpdate {
	// JDBC driver name and database URL
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/javalearning";

	// Database credentials
	static final String USER = "jamie";
	static final String PASS = "mysqldb";
	
	// Table creation SQL statements
    static final String sql_update_tasks = "UPDATE tasks SET priority = %d, begin_date = \'%s\', end_date = \'%s\' WHERE id = %d";

    
	public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;

		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      
			      //STEP 4: Update data
			      stmt = conn.createStatement();
			      
			      System.out.println("Updating rows in tasks...");
			      String sql = String.format(sql_update_tasks, 2, "2015-01-06", "2015-01-06", 4);
			      try {
			    	  System.out.println(sql);
			    	  stmt.executeUpdate(sql);
			      } catch(SQLException se){
			    	  //Handle errors for JDBC
					  se.printStackTrace();
			      }
			      			      
			      System.out.println("Data inserted successfully...");
			      
		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
		   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
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
	}

}
