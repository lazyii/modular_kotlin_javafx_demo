package org.rainday.avaj.mvvm;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HelloWorldViewModel {

    private SimpleStringProperty helloMessage = new SimpleStringProperty("Hello World");
    private SimpleStringProperty userName = new SimpleStringProperty("");


    private EventHandler<ActionEvent> buttonHandler = event -> {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                return ThreadLocalRandom.current().nextInt(0, 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -100;
        }).thenAcceptAsync(x -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(this.getHelloMessage() + "  " + this.getUserName());
            Platform.runLater(() -> {
                System.out.println(Thread.currentThread().getName());
                helloMessage.set(String.valueOf(x));
            });
        });
    };
    public StringProperty helloMessageProperty(){
        return helloMessage;
    }

    public String getHelloMessage(){
        return helloMessage.get();
    }

    public void setHelloMessage(String message){
        helloMessage.set(message);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public EventHandler<ActionEvent> getButtonHandler() {
        return buttonHandler;
    }

    public void setButtonHandler(EventHandler<ActionEvent> buttonHandler) {
        this.buttonHandler = buttonHandler;
    }
}
