import java.sql.*;

public class SQLiteTables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        Connection c = null;
        Statement stmt = null;
	      
        try {
        	Class.forName("org.sqlite.JDBC");
	        c = DriverManager.getConnection("jdbc:sqlite:D:\\sqlitedb\\javasqlite.db");
	        System.out.println("Opened database successfully");

	        stmt = c.createStatement();
        
	        String sql_create_projects_table = " CREATE TABLE IF NOT EXISTS projects ("+
	        		"id integer PRIMARY KEY,"+
	        		"name text NOT NULL,"+
	        		"begin_date text,"+
	        		"end_date text"+
	        		");";

	        stmt.executeUpdate(sql_create_projects_table);

	         String sql_create_tasks_table = "CREATE TABLE IF NOT EXISTS tasks ("+
	        		"id integer PRIMARY KEY,"+
	        		"name text NOT NULL,"+
	        		"priority integer,"+
	        		"status_id integer NOT NULL,"+
	        		"project_id integer NOT NULL,"+
	        		"begin_date text NOT NULL,"+
	        		"end_date text NOT NULL,"+
	        		"FOREIGN KEY (project_id) REFERENCES projects (id)"+
	        		");";

	         stmt.executeUpdate(sql_create_tasks_table);
	         stmt.close();
	         c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
	}
}
