package controllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Panier;
import model.Produit;
import serveurs.magasin.interfaces.MagasinInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tools {
    static void initMagasin(int id, Stage stage, MagasinInterface serveurMagasin, ArrayList<Panier> panierClient) throws IOException, SQLException {
        Scene scene = stage.getScene();
        AtomicInteger index = new AtomicInteger(1);
        List<Produit> produits =  serveurMagasin.getProduitsByMagasin(id);
        produits.forEach((produit) -> {
            Image image = new Image("images/"+produit.getIdProduit()+".jpg");
            ImageView img = (ImageView) scene.lookup("#img"+index);
            Label label = (Label) scene.lookup("#label"+index);
            Label labelPrix = (Label) scene.lookup("#labelPrix"+index);
            Label labelQte = (Label) scene.lookup("#labelQte"+index);
            Button plus = (Button) scene.lookup("#plus"+index);
            Button moins = (Button) scene.lookup("#moins"+index);
            Button addPanier = (Button) scene.lookup("#addPanier"+index);
            img.setImage(image);
            label.setText(produit.getNom());
            labelPrix.setText(String.valueOf(produit.getPrix()));
            if(panierClient != null) {
                Panier p = panierClient.stream()
                        .filter(panier -> produit.getIdProduit() == (panier.getIdProduit()))
                        .findAny()
                        .orElse(null);
                if (p != null) {
                    labelQte.setText(String.valueOf(produit.getQuantite()-p.getQteClient()));
                } else {
                    labelQte.setText(String.valueOf(produit.getQuantite()));
                }

            } else {
                labelQte.setText(String.valueOf(produit.getQuantite()));
            }
            moins.setDisable(true);
            addPanier.setDisable(true);
            if(Integer.parseInt(labelQte.getText()) > 0) {
                plus.setDisable(false);
            } else {
                plus.setDisable(true);
            }
            index.getAndIncrement();
        });
    }
}
