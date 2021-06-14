//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("layout.fxml"));
        primaryStage.setTitle(" MULTI_MEDIA ");
        primaryStage.setScene(new Scene(root, 800.0D, 300.0D));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
