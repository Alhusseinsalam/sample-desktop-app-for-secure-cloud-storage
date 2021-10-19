package dev.husein.sampledesktopapp.ui.eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EditUserBtn implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Edit User Button was Clicked");
    }
}