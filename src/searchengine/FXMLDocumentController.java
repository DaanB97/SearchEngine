/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Daan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnZoek;
    @FXML
    private Button btnSelecteerMap;
    @FXML
    private TextField tfZoek;
    @FXML
    private TextField tfBrowse;
    @FXML
    private ListView lvOverzicht;

    private String path;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String word = tfZoek.getText();
        Engine e = new Engine(word, path);
        Alert alert = new Alert(AlertType.WARNING);
        try {
            if ("".equals(word)) {
                alert.setTitle("Voer een woord in dat je wil zoeken");
                alert.show();
            } else {
                e.ClearList();
                e.GetFiles(path, word);
                ObservableList ol = e.getList();
                if (!ol.isEmpty()) {
                    lvOverzicht.setItems(ol);
                } else {
                    alert.setTitle("Geen bestanden gevonden met deze naam");
                    alert.show();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void btnSelecteerMap(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        path = dc.showDialog(btnSelecteerMap.getScene().getWindow()).toString();

        tfBrowse.setText(path);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
