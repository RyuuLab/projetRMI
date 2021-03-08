package serveurs.DAO;

import com.mysql.jdbc.Connection;
import model.ClientMagasin;
import model.Magasin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientMagasinDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();
    private static Statement statement;
    private static Connection conn;

    public static ClientMagasin toConnectMagasin(String email, String mdp) throws SQLException {
        String sql = "select * from ClientMagasin where email = ? and mdp = ?";
        ClientMagasin clientMagasin = null;
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, mdp);
        try (ResultSet res = ps.executeQuery();){
            while(res.next()) {
                clientMagasin = new ClientMagasin(
                        res.getInt("idClientMagasin"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getString("email"));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return clientMagasin;
    }
}