package restService.digeatal.service.data.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBConnector {
		
	public DBConnector() {
	}
	
	public abstract void connectDB() throws Exception;
	
	public abstract void disconnectDB() throws Exception;
	
	public abstract ResultSet readData() throws SQLException, ClassNotFoundException;
	
}
