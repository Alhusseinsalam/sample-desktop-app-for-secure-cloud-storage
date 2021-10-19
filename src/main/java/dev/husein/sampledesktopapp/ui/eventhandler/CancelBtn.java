package dev.husein.sampledesktopapp.ui.eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CancelBtn implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Cancel Button was Clicked");
    }
}
