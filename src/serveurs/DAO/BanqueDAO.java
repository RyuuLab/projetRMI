package serveurs.DAO;

import com.mysql.jdbc.Connection;
import model.Banque;
import model.ClientBanque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BanqueDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();
    private static Statement statement;
    private static Connection conn;

    public static Banque getBanque(int idBanque) throws SQLException {
        String sql = "select * from Banque where idBanque = ?";
        Banque banque = null;
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setInt(1, idBanque);
        try (ResultSet res = ps.executeQuery();){
            while(res.next()) {
                banque = new Banque(
                        res.getInt("idBanque"),
                        res.getString("nom"));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return banque;
    }
}
