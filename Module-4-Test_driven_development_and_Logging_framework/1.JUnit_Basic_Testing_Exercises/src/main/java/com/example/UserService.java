package com.example;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (findUserById(user.getId()) != null) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " already exists");
        }
        users.add(user);
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void removeUser(int id) {
        User user = findUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
        users.remove(user);
    }

    public int getUserCount() {
        return users.size();
    }

    public void clearAllUsers() {
        users.clear();
    }

    public static class User {
        private int id;
        private String name;
        private String email;

        public User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }

        public void setName(String name) { this.name = name; }
        public void setEmail(String email) { this.email = email; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            User user = (User) obj;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
        }
    }
}