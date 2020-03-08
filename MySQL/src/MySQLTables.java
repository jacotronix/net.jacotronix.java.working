import java.sql.*;
public class MySQLTables {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/javalearning";

	// Database credentials
	static final String USER = "jamie";
	static final String PASS = "mysqldb";
	
	// Table creation SQL statements
    static final String sql_create_projects_table = " CREATE TABLE IF NOT EXISTS projects ("+
    		"id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,"+
    		"name text NOT NULL,"+
    		"begin_date text,"+
    		"end_date text"+
    		");";
    static final String sql_create_tasks_table = "CREATE TABLE IF NOT EXISTS tasks ("+
    		"id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,"+
    		"name text NOT NULL,"+
    		"priority integer,"+
    		"status_id integer NOT NULL,"+
    		"project_id integer NOT NULL,"+
    		"begin_date text NOT NULL,"+
    		"end_date text NOT NULL,"+
    		"FOREIGN KEY (project_id) REFERENCES projects (id)"+
    		");";

	public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;

		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      
			      //STEP 4: Execute a query
			      stmt = conn.createStatement();
			      
			      System.out.println("Creating table: projects...");
			      stmt.executeUpdate(sql_create_projects_table);
			      System.out.println("Creating table: tasks...");
			      stmt.executeUpdate(sql_create_tasks_table);

			      System.out.println("Tables created successfully...");
			      
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

