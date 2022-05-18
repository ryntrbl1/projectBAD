package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

public class Connect {
	private Statement state;
	private ResultSet rs;
	private ResultSetMetaData rsm;
	private PreparedStatement ps;
	private Connection con;
	
	private static Connect instance = null;
	public static Connect getInstance() {
		if(instance == null) instance = new Connect(); 
		instance = new Connect();
		return instance;
		
	}
	//select query
	
	
	public ResultSet execQuery(String query) {
		rs = null;
		try {
			rs = state.executeQuery(query);
//			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return rs;
	}
	
	public int executeUpdate(String query) {
		try {
			return state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public PreparedStatement prep(String query) {
		ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	//login


	public Connect() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/cakeland","root",
				"");
			//create Statement
			state = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
