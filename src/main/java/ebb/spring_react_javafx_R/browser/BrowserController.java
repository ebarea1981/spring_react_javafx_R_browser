package ebb.spring_react_javafx_R.browser;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {

    @FXML
    WebView webView;
    private WebEngine webEngine;

    @FXML
    private Button btnGo;

    @FXML
    private TextField tvUrl;

    @FXML
    private ProgressBar progressBar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        webEngine = webView.getEngine();
        // Removing right clicks
        webView.setContextMenuEnabled(false);
        //Setting button action
        btnGo.setOnAction((event -> loadWebsite()));
        // updating progress bar using binding
        progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
        webEngine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        // Hide progress bar then page is ready
                        progressBar.setVisible(false);
                    } else {
                        //Showing progress bar
                        progressBar.setVisible(true);
                    }
                });
    }

    private void loadWebsite() {
        System.out.println("Loading your URL...");
        try {
            webEngine.load(tvUrl.getText());
        } catch (Exception e) {
            System.out.println("Url Problem:" + e.getCause().getMessage());
            e.printStackTrace();
        }
    }
}