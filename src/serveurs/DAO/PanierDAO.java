package serveurs.DAO;

import com.mysql.jdbc.Connection;
import model.Panier;
import model.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PanierDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();
    private static Statement statement;
    private static Connection conn;

    public static List<Panier> getPanierClient(int idClient) throws SQLException {
        String sql = "select * from Panier where idClient = ?";
        List<Panier> paniers = new ArrayList<Panier>();
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setInt(1, idClient);
        try (ResultSet res = ps.executeQuery();) {
            while (res.next()) {
                Panier panier = new Panier(
                        res.getInt("idPanier"),
                        res.getInt("idClient"),
                        ProduitDAO.getProduit(res.getInt("idProduit")),
                        res.getInt("quantite"));
                paniers.add(panier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paniers;
    }

    public static int addPanier(int idClient, int IdProduit, int qteProduit) throws SQLException {
        int idPanier = 0;
        String sql = "INSERT into Panier (idClient, idProduit, qteProduit) VALUES (?,?,?);";
        PreparedStatement ps = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, idClient);
        ps.setInt(2, IdProduit);
        ps.setInt(3, qteProduit);
        try {
            ps.executeUpdate();
            ResultSet tableKeys = ps.getGeneratedKeys();
            tableKeys.next();
            idPanier = tableKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPanier;
    }
}
