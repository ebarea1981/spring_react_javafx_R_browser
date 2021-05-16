package ebb.spring_react_javafx_R.browser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class BrowserApp extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Loading launcher view
        final URL location = getClass().getResource("home-browser.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        final Parent root = fxmlLoader.load(location.openStream());
        //Creating scene
        Scene scene = new Scene(root);
        stage.setTitle("React Browser");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        //Showing application
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
