package serveurs.DAO;

import com.mysql.jdbc.Connection;
import model.ClientBanque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientBanqueDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();
    private static Statement statement;
    private static Connection conn;

    public static ClientBanque toConnectBanque(int cb, int cvv) throws SQLException {
        String sql = "select * from ClientBanque where cb = ? and cvv = ?";
        ClientBanque ClientBanque = null;
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setInt(1, cb);
        ps.setInt(2, cvv);
        try (ResultSet res = ps.executeQuery();){
            while(res.next()) {
                ClientBanque = new ClientBanque(
                        res.getInt("idClientBanque"),
                        res.getInt("idBanque"),
                        res.getInt("cb"),
                        res.getInt("cvv"));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return ClientBanque;
    }

    public static Boolean toVerifBanque(int cb, int cvv, String mdp) throws SQLException {
        String sql = "select * from ClientBanque where cb = ? and cvv = ? and mdp = ?";
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setInt(1, cb);
        ps.setInt(2, cvv);
        ps.setString(2, mdp);
        try (ResultSet res = ps.executeQuery();){
            if(res.next()) {
                return true;
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
