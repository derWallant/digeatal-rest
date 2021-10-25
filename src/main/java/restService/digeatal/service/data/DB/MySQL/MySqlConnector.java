package restService.digeatal.service.data.DB.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

import restService.digeatal.service.data.DB.DBConnector;

public class MySqlConnector extends DBConnector {

	private Statement statement= null;
	private Connection connObj = null;
	private ResultSet resultSet = null;

	private DbSchema schemaObj;
	private DbSpec specficationObj;
	
	private String tab_name;
	private DbTable table_name;
	private DbColumn column_1, column_2, column_3, column_4;

	private String database = "digeatal_local";
	private String host = "localhost";
	private String user = "webApp";
	private String pw = "init2021";
	private String conString;
	private String query;

	public MySqlConnector(String tabName) throws Exception {
		this.tab_name = tabName;
		this.conString = "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + pw;
		connectDB();
	}

	@Override
	public void connectDB() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connObj = DriverManager.getConnection(this.conString);
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public void disconnectDB() {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet readData() throws SQLException, ClassNotFoundException {
		statement = connObj.createStatement();

		// Result set get the result of the SQL query
		this.query = "select * from " + database + "." + tab_name ;
		resultSet = statement.executeQuery(this.query);
	//	resultSet = statement.executeQuery("select * from digeatal_local.Restaurants");
	    disconnectDB();
		return resultSet;
	}

}
