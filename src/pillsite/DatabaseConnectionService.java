package pillsite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
		private Connection connection = null;

		private String databaseName;
		private String serverName;

		public DatabaseConnectionService(String serverName, String databaseName) {
			this.serverName = serverName;
			this.databaseName = databaseName;
		}

		public boolean connect(String user, String pass) {
			String connectionUrl ="jdbc:sqlserver://"+this.serverName+";databaseName="+this.databaseName+";user="+user+";password="+pass;

	        try {
	        	connection = DriverManager.getConnection(connectionUrl);
	        	return true;
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	    		return false;
	        }
		}
		

		public Connection getConnection() {
			return this.connection;
		}
		
		public void closeConnection() throws SQLException {
			connection.close();
		}

	}
