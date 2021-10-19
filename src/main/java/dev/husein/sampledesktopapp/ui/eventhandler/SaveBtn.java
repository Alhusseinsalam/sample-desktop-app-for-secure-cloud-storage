package dev.husein.sampledesktopapp.ui.eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveBtn implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Save Button was Clicked");
    }
}
