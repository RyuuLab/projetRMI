import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import serveurs.DAO.MysqlConnect;

public class main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement;
		System.out.println(MysqlConnect.getDbCon());
		
		System.out.println(MysqlConnect.query("select * from Banque;"));
	}

}
