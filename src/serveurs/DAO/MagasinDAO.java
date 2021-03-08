package serveurs.DAO;

import com.mysql.jdbc.Connection;
import model.Magasin;
import model.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MagasinDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();
    private static Statement statement;
    private static Connection conn;

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
