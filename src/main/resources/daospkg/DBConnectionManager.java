package daospkg;
import java.sql.Connection;
import java.sql.DriverManager;

/*Database connection class*/
public class DBConnectionManager {
	
	 //database connection info
	static Connection connection;
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/GPSystem";
    static String dbuser = "root";
    static String dbpass = "";
    
	public static Connection getConnection()
     {
		try{
			
			Class.forName(driver);
            System.out.println("Driver loaded successfully.");
            connection = DriverManager.getConnection(url, dbuser, dbpass);
            System.out.println("Connection Successful.");
		}catch(Exception ex) {
			  if (connection == null) {
	                System.out.println("Connection not successful.");
	            }//end if
	 
	            ex.printStackTrace();
		}//end catch
		return connection;
     }//end get connection
}//end class DBConnectionManager
