package sample.Decryption;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Controller;
import sample.Encryption.EncryptionController;
import sample.Main;
import sample.PlayFair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DecryptionController {

    @FXML
    private Pane decryptionPane;
    @FXML
    public  TextArea encryptedTextArea;
    @FXML
    private TextArea decryptedTextArea;


    @FXML
    private void loadMainStage() {
        loadMainScene();
    }

    public void loadFromEncryption(){
        String textToDecryption = EncryptionController.getEncryptedText();
        encryptedTextArea.setText(textToDecryption);
    }

    @FXML
    private void saveToFile() {
        if(decryptedTextArea != null) {
            String encryptedText = decryptedTextArea.getText();
            try (PrintWriter outstream = new PrintWriter("decryptionResult.txt")) {
                outstream.println(encryptedText + "\n");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void loadFromFile() throws IOException {
        String path ="D:\\Program Files (x86)\\Projekty\\Enigma\\result.txt";
        String content = String.valueOf(Files.readAllLines(Paths.get(path)));
        encryptedTextArea.setText(content);
    }

    @FXML
    private void decryption() {
        String textToDecryption = encryptedTextArea.getText();
        String decryptedText = "";
        try {
            decryptedText = PlayFair.decrypt(textToDecryption);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getStackTrace();
        }

        decryptedTextArea.setText(decryptedText);
    }

    public void loadMainScene() {
        try {
            Parent mainView = (Pane) FXMLLoader.load(getClass().getResource("../sample.fxml"));

            Scene mainScene = new Scene(mainView);

            Stage curStage = (Stage) decryptionPane.getScene().getWindow();
            Main main = new Main();
            main.start(curStage);
            curStage.setScene(mainScene);

        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public TextArea getEncryptedTextArea() {
        return encryptedTextArea;
    }
}
