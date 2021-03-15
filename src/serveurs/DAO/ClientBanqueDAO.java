package serveurs.DAO;

import model.ClientBanque;
import model.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ClientBanqueDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();

    public static ClientBanque toConnectBanque(long cb, int cvv) throws SQLException {
        String sql = "select * from ClientBanque where cb = ? and cvv = ?";
        ClientBanque ClientBanque = null;
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setLong(1, cb);
        ps.setInt(2, cvv);
        try (ResultSet res = ps.executeQuery();){
            while(res.next()) {
                ClientBanque = new ClientBanque(
                        res.getInt("idClientBanque"),
                        res.getInt("idBanque"),
                        res.getLong("cb"),
                        res.getInt("cvv"));
            }
        } catch (SQLException e ) {
            System.out.println(7);
            e.printStackTrace();
        }
        return ClientBanque;
    }

    public static Boolean toVerifBanque(long cb, int cvv, String mdp) throws SQLException {
        String sql = "select * from ClientBanque where cb = ? and cvv = ? and mdp = ?";
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setLong(1, cb);
        ps.setInt(2, cvv);
        ps.setString(3, mdp);
        try (ResultSet res = ps.executeQuery();){
            if(res.next()) {
                return true;
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean toVerifSolde(int idClient, double totalAchat) throws SQLException {
        String sql = "select * from ClientBanque where idClientBanque = ? and solde >= ?";
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setLong(1, idClient);
        ps.setDouble(2, totalAchat);
        try (ResultSet res = ps.executeQuery();){
            if(res.next()) {
                return true;
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateSolde(int idClient, double achatTotal) throws SQLException {
        String sql = "UPDATE ClientBanque SET solde = (select solde from ClientBanque where idClientBanque = ?)-? where idClientBanque = ?;";
        PreparedStatement ps = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, idClient);
        ps.setDouble(2, achatTotal);
        ps.setInt(3, idClient);
        try {
                ps.executeUpdate();
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
