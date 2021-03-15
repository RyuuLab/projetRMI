package controllers;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import model.*;
import serveurs.magasin.controllers.ConnectToBanque;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;


public class PopupBanque {


    public static void display(ClientBanque client, double totalAchat, Banque banque) throws RemoteException, SQLException {

        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(banque.getNom());

        Label nomBanque= new Label(banque.getNom());
        Label error= new Label("");
        Label total= new Label("Montant de vos achats : " +totalAchat+ " euros");
        error.setTextFill(Color.web("#E80000"));

        Label label= new Label("Confimer votre compte");
        PasswordField mdp = new PasswordField();

        Button valider= new Button("Valider");
        Button annuler= new Button("Annuler");
        Button magasin= new Button("Retourner au magasin");
        magasin.setVisible(false);



        valider.setOnAction(e ->
        {
            error.setText("");
            boolean connect = false;
            boolean solvable = false;
            try {
                connect = ConnectToServeur.banque().toVerifBanque(client.getCb(), client.getCvv(), mdp.getText());
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(connect){
                try {
                    solvable = ConnectToServeur.banque().toVerifSolde(client.getIdClientBanque(), totalAchat);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if(solvable) {
                    error.setText("Votre achat a ete confirme");
                    error.setTextFill(Color.web("#26E807"));
                    error.setVisible(true);
                    mdp.setVisible(false);
                    try {
                        ConnectToServeur.magasin().achatTermine(true);
                        valider.setVisible(false);
                        annuler.setVisible(false);
                        magasin.setVisible(true);
                        ConnectToBanque.banque().updateSolde(client.getIdClientBanque(), totalAchat);
                    } catch (RemoteException | SQLException remoteException) {
                        remoteException.printStackTrace();
                    }
                } else {
                    try {
                        ConnectToServeur.magasin().achatTermine(false);
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                    error.setText("solde insuffisant");
                    error.setVisible(true);
                }
            } else {
                try {
                    ConnectToServeur.magasin().achatTermine(false);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
                error.setText("mot de passe incorrect");
                error.setVisible(true);
                mdp.setText("");
            }

        }
         );


        annuler.setOnAction(e -> popupwindow.close());
        magasin.setOnAction(e -> popupwindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(nomBanque, label,total,error, mdp, magasin,valider, annuler);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}
