package restService.digeatal.service.data.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	private String table;
	private String database = "digeatal_local";
	private String host = "localhost";
	private String user = "webApp";
	private String pw = "init2021";
	private String connection;
	private String query;

	public MySQLAccess(String tabName) {
		this.table = tabName;
		this.connection = "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + pw;
	}

	public ResultSet readDataBase() throws SQLException, ClassNotFoundException {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection(this.connection);
//                    .getConnection("jdbc:mysql://localhost/digeatal_local?"
//                            + "user=webApp&password=init2021");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		// Result set get the result of the SQL query
		this.query = "select ";
		resultSet = statement.executeQuery("select * from digeatal_local.Restaurants");
		// close();
		return resultSet;
		// writeResultSet(resultSet);
	}

//    private void writeMetaData(ResultSet resultSet) throws SQLException {
//        //  Now get some metadata from the database
//        // Result set get the result of the SQL query
//
//        System.out.println("The columns in the table are: ");
//
//        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
//        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
//            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
//        }
//    }

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String id = resultSet.getString("Id");
			String name = resultSet.getString("Name");
			System.out.println("Id: " + id);
			System.out.println("Restaurant: " + name);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
