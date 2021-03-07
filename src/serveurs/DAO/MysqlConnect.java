package serveurs.DAO;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MysqlConnect {
    public Connection conn;
    public static MysqlConnect db;
    private static Statement statement;
    
    private MysqlConnect() {
        String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/";
        String dbName = "dengler5u_RMI";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "dengler5u_appli";
        String password = "31628479";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("base connecté : " + this.conn);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static synchronized MysqlConnect getDbCon() {
        if (db == null) {
            db = new MysqlConnect();
        }
        return db;
    }
    
    public static ResultSet query(String query) throws SQLException{
        statement = (Statement) db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        while(res.next()) {
        	System.out.println(res.getDouble("idBanque"));
        	System.out.println(res.getString("nom"));
        	  }
        return res;
       
    }
}