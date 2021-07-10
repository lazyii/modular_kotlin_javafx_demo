package org.rainday.avaj.mvvm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StarterApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World Application");

        //ViewTuple<HelloWorldView, HelloWorldViewModel> viewTuple = FluentViewLoader.fxmlView(HelloWorldView.class).load();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/HelloWorld.fxml"));
        stage.setScene(new Scene(root));
        stage.show();


    }
}
