package dev.husein.sampledesktopapp.ui;

import com.google.common.base.Strings;
import dev.husein.sampledesktopapp.model.User;
import dev.husein.sampledesktopapp.storage.UserStorage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UsersApp extends Application {
    @Override
    public void start(Stage mainStage) throws Exception {
        UserStorage.createDataFile();
        mainStage.setTitle("Users Management");
        mainStage.setScene(getMainScene());
        mainStage.show();
    }

    private static TableView<User> table = new TableView<>();
    private static TextField tfId = new TextField();
    private static TextField tfName = new TextField();
    private static TextField tfAge = new TextField();

    private static String getSelectedUserId() {
        return ((User) table.getSelectionModel().selectedItemProperty().get()).getUserId();
    }

    private static User getSelectedUser() {
        return UserStorage.getUserById(getSelectedUserId());
    }

    private static Scene getUserEditScene() {
        GridPane pane = getUserEditPane();
        return new Scene(pane, 500, 500);
    }

    private static void loadDataIntoTable() {
        List<User> users = UserStorage.openDataFile().getUsers();
        ArrayList<User> usersTableList = new ArrayList<User>();
        for (User user : users) {
            User userRow = new User();

            userRow.setUserId(user.getUserId());
            userRow.setName(user.getName());
            userRow.setAge(user.getAge());

            usersTableList.add(userRow);
        }

        ObservableList<User> usersTableData = FXCollections.observableArrayList(usersTableList);
        table.setItems(usersTableData);
        table.getSelectionModel().selectFirst();
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

        tfAge.setPrefColumnCount(3);

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(actionEvent -> {
            User user = new User(tfId.getText(), tfName.getText(), Integer.parseInt(tfAge.getText()));
            UserStorage.persistUser(user);

            loadDataIntoTable();
            tfId.clear();
            tfAge.clear();
            tfName.clear();
        });

        pane.add(lblId, 0, 0);
        pane.add(tfId, 1, 0);

        pane.add(lblName, 0, 1);
        pane.add(tfName, 1, 1);

        pane.add(lblAge, 0, 2);
        pane.add(tfAge, 1, 2);

        pane.add(btnAdd, 0, 3);

        return pane;
    }

    private static Scene getMainScene() {
        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(15,5,5,5));

        GridPane buttonsPane = new GridPane();
        buttonsPane.setVgap(20);
        buttonsPane.setVgap(20);

        // add button to sync
        Button btnSync = new Button("Sync");
        btnSync.setOnAction(actionEvent -> {
            System.out.println("Syncing");
        });

        // add button to edit
        Button btnEditUser = new Button("Edit");
        btnEditUser.setOnAction(actionEvent -> {
            User user = getSelectedUser();
            tfId.setText(user.getUserId());
            tfName.setText(user.getName());
            tfAge.setText(Integer.toString(user.getAge()));
        });

        // button to delete
        Button btnDeleteUser =  new Button("Delete");
        btnDeleteUser.setOnAction(actionEvent -> {
            System.out.println("Delete User Button was Clicked");
            String userId = getSelectedUserId();
            System.out.println("Selected to Delete: " + userId);
            UserStorage.deleteUserById(userId);
            System.out.println(actionEvent);
            loadDataIntoTable();
        });

        buttonsPane.add(btnEditUser, 0, 0);
        buttonsPane.add(btnDeleteUser, 1, 0);
        buttonsPane.add(btnSync, 2, 0);

        vBox.getChildren().add(getUsersListTable());
        vBox.getChildren().add(buttonsPane);
        vBox.getChildren().add(getUserEditPane());

        return new Scene(vBox, 500, 500);
    }

    private static TableView getUsersListTable() {
        loadDataIntoTable();
        final Label label = new Label("Users List");

        TableColumn userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn userNameCol = new TableColumn<>("User Name");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn userAgeCol = new TableColumn<>("User Age");
        userAgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(userIdCol, userNameCol, userAgeCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        table.getSelectionModel().selectedItemProperty().addListener((observableValue, usersTable, newValue) -> {
            System.out.println(newValue);
        });

        return table;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
