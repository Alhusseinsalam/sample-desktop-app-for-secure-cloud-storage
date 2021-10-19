package dev.husein.sampledesktopapp;

import dev.husein.sampledesktopapp.dao.UsersFile;
import dev.husein.sampledesktopapp.model.User;
import dev.husein.sampledesktopapp.storage.UserStorage;
import dev.husein.sampledesktopapp.ui.UsersApp;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("billy22", "junior", 12);
        User user2 = new User("redbad23", "lee", 11);
        User user3 = new User("manworld2", "jackson", 45);

//        UsersFile uf  = new UsersFile();
//        uf.addUserToList(user1);
//        uf.addUserToList(user2);
//
//        UserStorage.createDataFile();
//        UserStorage.storeDataToExistingFile(uf);
//
//        uf = UserStorage.openDataFile();
//        System.out.println(uf);

//        UserStorage.storeNewUser(user1);
//        UserStorage.storeNewUser(user2);
//        UserStorage.storeNewUser(user3);
//        uf = UserStorage.openDataFile();
//        System.out.println(uf);
//
//        uf.removeUserFromList(user3);
//        UserStorage.storeDataToExistingFile(uf);
//        uf = UserStorage.openDataFile();
//        System.out.println(uf);

    }
}
