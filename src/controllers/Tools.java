package controllers;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Produit;
import serveurs.magasin.interfaces.MagasinInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tools {
    static void initMagasin(int id, Stage stage, MagasinInterface serveurMagasin) throws IOException, SQLException {
        Scene scene = stage.getScene();
        AtomicInteger index = new AtomicInteger(1);
        List<Produit> produits =  serveurMagasin.getProduitsByMagasin(id);
        produits.forEach((produit) -> {
            Image image = new Image("images/"+produit.getIdProduit()+".jpg");
            ImageView img = (ImageView) scene.lookup("#img"+index);
            Label label = (Label) scene.lookup("#label"+index);
            Label labelPrix = (Label) scene.lookup("#labelPrix"+index);
            Label labelQte = (Label) scene.lookup("#labelQte"+index);
            img.setImage(image);
            label.setText(produit.getNom());
            labelPrix.setText(String.valueOf(produit.getPrix()));
            labelQte.setText(String.valueOf(produit.getQuantite()));
            index.getAndIncrement();
        });
    }
}
