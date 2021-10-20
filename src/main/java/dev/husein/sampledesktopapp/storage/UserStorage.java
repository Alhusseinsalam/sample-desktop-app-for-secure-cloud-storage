package dev.husein.sampledesktopapp.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.husein.sampledesktopapp.dao.UsersFile;
import dev.husein.sampledesktopapp.model.User;

import java.io.File;
import java.io.IOException;


public class UserStorage {
    private static final String USERS_DATA_FILE_NAME = "Users.json";

    public static void createDataFile() {
        try {
            File file = new File(USERS_DATA_FILE_NAME);
            if (file.exists()) return;
            ObjectMapper mapper = new ObjectMapper();
            mapper.writer().writeValue(file, new UsersFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UsersFile openDataFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(USERS_DATA_FILE_NAME);
            return mapper.reader().readValue(file, UsersFile.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void storeDataToExistingFile(UsersFile uf) {
        try {
            File file = new File(USERS_DATA_FILE_NAME);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writer().writeValue(file, uf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void persistUser(User user) {
        UsersFile uf = openDataFile();
        User existingUser = getUserById(user.getUserId());
        if (existingUser != null) {
            uf.removeUserFromList(existingUser);
        }
        uf.addUserToList(user);
        storeDataToExistingFile(uf);
    }

    public static User getUserById(String id) {
        UsersFile uf = openDataFile();
        for (User user : uf.getUsers()) {
            if (user.getUserId().equals(id)) return user;
        }
        return null;
    }

    public static void deleteUserById(String id) {
        UsersFile uf = openDataFile();
        User user = getUserById(id);
        if (user != null) {
            uf.removeUserFromList(user);
            storeDataToExistingFile(uf);
        }
    }
}
