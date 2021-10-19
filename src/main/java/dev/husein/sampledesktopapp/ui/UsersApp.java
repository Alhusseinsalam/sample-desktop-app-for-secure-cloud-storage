package dev.husein.sampledesktopapp.ui;

import dev.husein.sampledesktopapp.model.User;
import dev.husein.sampledesktopapp.storage.UserStorage;
import dev.husein.sampledesktopapp.ui.eventhandler.*;
import dev.husein.sampledesktopapp.ui.model.UsersTable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.script.Bindings;
import java.util.ArrayList;
import java.util.List;

public class UsersApp extends Application {
    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Users Management");
        mainStage.setScene(getUserEditScene());
        mainStage.show();
    }

    private static Scene getUserEditScene() {
        GridPane pane = getUserEditPane();
        return new Scene(pane, 500, 500);
    }

    private static GridPane getUserEditPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(11,12,13,14));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);

        Label lblId = new Label("User ID: ");
        Label lblName = new Label("Name: ");
        Label lblAge = new Label("Age: ");

        TextField tfId = new TextField();
        TextField tfName = new TextField();
        TextField tfAge = new TextField();
        tfAge.setPrefColumnCount(3);

        Button btnSave = new Button("Save");
        SaveBtn saveBtn = new SaveBtn();
        btnSave.setOnAction(saveBtn);

        Button btnCancel = new Button("Cancel");
        CancelBtn cancelBtn = new CancelBtn();
        btnCancel.setOnAction(cancelBtn);

        pane.add(lblId, 0, 0);
        pane.add(tfId, 1, 0);

        pane.add(lblName, 0, 1);
        pane.add(tfName, 1, 1);

        pane.add(lblAge, 0, 2);
        pane.add(tfAge, 1, 2);

        pane.add(btnSave, 0, 3);
        pane.add(btnCancel, 1, 3);

        return pane;
    }

    private static Scene getMainScene() {
        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(15,5,5,5));

        GridPane buttonsPane = new GridPane();
        buttonsPane.setVgap(20);
        buttonsPane.setVgap(20);

        Button btnSync = new Button("Sync");
        btnSync.setOnAction(new SyncBtn());

        buttonsPane.add(btnSync, 0, 0);

        vBox.getChildren().add(getUsersListTable());
        vBox.getChildren().add(buttonsPane);

        return new Scene(vBox, 500, 500);
    }

    private static TableView getUsersListTable() {
        List<User> users = UserStorage.openDataFile().getUsers();
        ArrayList<UsersTable> usersTableList = new ArrayList<UsersTable>();
        for (User user : users) {
            UsersTable usersTable = new UsersTable();
            usersTable.setUserId(user.getUserId());
            // add button to edit
            Button btnEditUser = new Button("Edit");
            btnEditUser.setOnAction(new EditUserBtn());
            usersTable.setEditBtn(btnEditUser);
            // button to delete
            Button btnDeleteUser =  new Button("Delete");
            btnDeleteUser.setOnAction(new DeleteUserBtn());
            usersTable.setDeleteBtn(btnDeleteUser);
            usersTableList.add(usersTable);
        }
        ObservableList<UsersTable> usersTableData = FXCollections.observableArrayList(usersTableList);

        TableView<UsersTable> table = new TableView<>();
        final Label label = new Label("Users List");

        TableColumn userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn editBtnCol = new TableColumn<>("Edit");
        editBtnCol.setCellValueFactory(new PropertyValueFactory<>("editBtn"));

        TableColumn deleteBtnCol = new TableColumn<>("Delete");
        deleteBtnCol.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        table.setItems(usersTableData);
        table.getColumns().addAll(userIdCol, editBtnCol, deleteBtnCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        return table;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
