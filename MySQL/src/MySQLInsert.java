import java.sql.*;

public class MySQLInsert {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/javalearning";

	// Database credentials
	static final String USER = "jamie";
	static final String PASS = "mysqldb";
	
	// Table creation SQL statements
    static final String sql_insert_into_projects_table = "INSERT INTO projects" +
    		"(name,begin_date,end_date)";
    
    static final String sql_inserts_into_tasks_table = "INSERT INTO tasks" +
    		"(name,priority,status_id,project_id,begin_date,end_date)";

	static final String project = "VALUES (\'Cool App with SQLite & Python\', \'2015-01-01\', \'2015-01-30\');";
	static final String task_1 = " VALUES (\'Analyze the requirements of the app\', "
			+ "\'1\', \'1\', \'%d\', \'2015-01-01\', \'2015-01-02\');";
	static final String task_2 = " VALUES (\'Confirm with user about the top requirements\', "
			+ "\'1\', \'1\', \'%d\', \'2015-01-03\', \'2015-01-05\');";

    private static int insertIntoProjects(Statement st, String sql, String vals)
    {
    	ResultSet rs = null;
    	int autoIncKeyFromFunc = -1;
    	System.out.println("Inserting rows in to projects...");
    	try {
    		st.executeUpdate(sql + vals);
    	    rs = st.executeQuery("SELECT LAST_INSERT_ID()");
    	    if (rs.next()) {
    	        autoIncKeyFromFunc = rs.getInt(1);
    	    }
    	} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
    	}
        System.out.println("Key returned from 'SELECT LAST_INSERT_ID()': " + autoIncKeyFromFunc);
    	return autoIncKeyFromFunc;
    }
    
    private static void insertIntoTasks(Statement st, String sql, String vals, int id)
    {
    	System.out.println("Inserting rows in to tasks...");
    	try {
    		System.out.println(sql + String.format(vals, id));
    		st.executeUpdate(sql + String.format(vals, id));
     	} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
    	}
    }
    
	public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   int refID = 0;

		   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      
			      //STEP 4: Insert data
			      stmt = conn.createStatement();
			      
			      refID = insertIntoProjects(stmt, sql_insert_into_projects_table, project);
			      
			      insertIntoTasks(stmt, sql_inserts_into_tasks_table, task_1, refID);
			      insertIntoTasks(stmt, sql_inserts_into_tasks_table, task_2, refID);
			      
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


