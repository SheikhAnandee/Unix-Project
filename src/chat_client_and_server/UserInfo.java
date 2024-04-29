/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_client_and_server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Sakib
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L; // Version UID for serialization
    private static Map<String, String> users;

    public UserInfo() {
        users = new HashMap<>();
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void addUser(String username, String password) {
        users.put(username, password);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    // Get the password associated with a username
    public String getPassword(String username) {
        return users.get(username);
    }

    // Get the username associated with a password
    public String getUsername(String password) {
        for (Map.Entry<String, String> entry : users.entrySet()) {
            if (entry.getValue().equals(password)) {
                return entry.getKey();
            }
        }
        return null; // Return null if the password is not found
    }
}

