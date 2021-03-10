package serveurs.DAO;

import model.Banque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BanqueDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();

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
