package serveurs.DAO;

import model.Magasin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagasinDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();

    public static List<Magasin> getMagasins() throws SQLException {
        String sql = "select * from Magasin";
        List<Magasin> magasins =  new ArrayList<Magasin>() ;
        PreparedStatement ps = db.conn.prepareStatement(sql);
        try (ResultSet res = ps.executeQuery();){
            while(res.next()) {
                Magasin magasin = new Magasin(
                        res.getInt("idMagasin"),
                        res.getString("nomMagasin"),
                        res.getString("adresse"),
                        res.getString("ville"));
                magasins.add(magasin);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return magasins;
    }
}
