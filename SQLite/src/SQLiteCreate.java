import java.sql.*;

public class SQLiteCreate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SQLite Create");
	      Connection c = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:D:\\sqlitedb\\javasqlite.db");
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}

}
