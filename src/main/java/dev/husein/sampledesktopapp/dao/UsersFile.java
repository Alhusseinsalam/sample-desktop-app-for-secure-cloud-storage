package dev.husein.sampledesktopapp.dao;

import dev.husein.sampledesktopapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersFile {
    private long count;
    private List<User> users;

    public UsersFile() {
        count = 0;
        users = new ArrayList<>();
    }

    public void addUserToList(User user) {
        users.add(user);
    }

    public void removeUserFromList(User user) {
        this.users.remove(user);
    }

    public long getCount() {
        return this.users.size();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersJson {" +
                "count=" + count +
                ", users=" + users +
                '}';
    }
}
