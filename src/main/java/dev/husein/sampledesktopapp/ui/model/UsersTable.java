package dev.husein.sampledesktopapp.ui.model;

import javafx.scene.control.Button;

public class UsersTable {
    private String userId;
    private Button editBtn;
    private Button deleteBtn;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Button getEditBtn() {
        return editBtn;
    }

    public void setEditBtn(Button editBtn) {
        this.editBtn = editBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    @Override
    public String toString() {
        return "UsersTable{" +
                "userId='" + userId + '\'' +
                ", editBtn=" + editBtn +
                ", deleteBtn=" + deleteBtn +
                '}';
    }

}
