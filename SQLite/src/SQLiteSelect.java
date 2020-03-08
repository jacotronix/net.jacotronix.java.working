import java.sql.*;

public class SQLiteSelect {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.sqlite.JDBC";  
	static final String DB_URL = "jdbc:sqlite:D:\\sqlitedb\\javasqlite.db";

	// Table creation SQL statements
    static final String sql_update_tasks = "UPDATE tasks SET priority = %d, begin_date = \'%s\', end_date = \'%s\' WHERE id = %d";

    private static ResultSet allTasks(Statement stmt) {
    	ResultSet rs = null;
	    String sql = "SELECT * from tasks";
	    try {
	    	System.out.println(sql);
	    	rs = stmt.executeQuery(sql);
		      while (rs.next()) {
		    	  int id = rs.getInt("id");
		    	  String name = rs.getString("name");
		    	  int pri = rs.getInt("priority");
		    	  int sta = rs.getInt("status_id");
		    	  int pro = rs.getInt("project_id");
		    	  
		    	  System.out.print(id + "\t");
		    	  System.out.print(name + "\t\t");
		    	  System.out.print(pri + "\t");
		    	  System.out.print(sta + "\t");
		    	  System.out.print(pro + "\t");
		    	  System.out.println();
		      }
	    } catch(SQLException se){
	    	//Handle errors for JDBC
	    	se.printStackTrace();
	    }
    	return rs;
    }

    private static void proTasks(Statement stmt, int priority) {
    	ResultSet rs = null;
	    String sql = String.format("SELECT * from tasks WHERE priority=%d", priority);
	    try {
	    	System.out.println(sql);
	    	rs = stmt.executeQuery(sql);
		    while (rs.next()) {
		    	  int id = rs.getInt("id");
		    	  String name = rs.getString("name");
		    	  int pri = rs.getInt("priority");
		    	  int sta = rs.getInt("status_id");
		    	  int pro = rs.getInt("project_id");
		    	  
		    	  System.out.print(id + "\t");
		    	  System.out.print(name + "\t\t");
		    	  System.out.print(pri + "\t");
		    	  System.out.print(sta + "\t");
		    	  System.out.print(pro + "\t");
		    	  System.out.println();
		      }
	    } catch(SQLException se){
	    	//Handle errors for JDBC
	    	se.printStackTrace();
	    }
}
    
    
	public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;

		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL);
			      
			      //STEP 4: Update data
			      stmt = conn.createStatement();
			      
			      allTasks(stmt);
			      
			      proTasks(stmt, 1);
			      
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

