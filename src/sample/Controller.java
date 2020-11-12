package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {


    @FXML
    protected Pane rootPane;

    @FXML
    private void handleDecryptionClick() {
        String placement = "Decryption/Decryption.fxml";
      //  String textToDecryption = EncryptionController.getEncryptedText();
        //encryptedTextArea.setText(textToDecryption);
        loadNextScene(placement);
    }

    @FXML
    private void handleEncryptionClick() {
        String placement = "Encryption/Encryption.fxml";
        loadNextScene(placement);
    }

    @FXML
    private void handleAboutClick() {
        String placement = "About/About.fxml";
        loadNextScene(placement);
    }


    @FXML
    private void handleExitClick() {
        try {
            Runtime.getRuntime().exit(0);
        } catch (Exception e) {
            e.getMessage();
            System.exit(1);
        }

    }

    protected void loadNextScene(String placement) {
        try {
            Parent decryptionView;
            decryptionView = (Pane) FXMLLoader.load(getClass().getResource(placement));

            Scene decryptionScene = new Scene(decryptionView);

            Stage curStage = (Stage) rootPane.getScene().getWindow();

            curStage.setScene(decryptionScene);

        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
