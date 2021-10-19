package dev.husein.sampledesktopapp.ui.eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DeleteUserBtn implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Delete User Button was Clicked");
    }
}