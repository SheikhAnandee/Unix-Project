package chat_client_and_server;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.*;

public class UserDatabaseDeserialization {

    private static final Logger errorLogger = Logger.getLogger("error");
    // Declare a static userDatabase instance to store the loaded user data
    private static UserInfo userDatabase;
    private static Map<String, String> users;

    /**
     *
     * @return
     */
    public ArrayList<UserInfo> getUserData() {
        ArrayList<UserInfo> userDataList = new ArrayList<>();
        String filePath = "user_database.ser";

        try (FileInputStream fileIn = new FileInputStream(filePath); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            // Deserialize the object as an ArrayList<UserInfo>
            userDataList = (ArrayList<UserInfo>) objectIn.readObject();

        } catch (IOException | ClassNotFoundException e) {
            // Handle errors
            e.printStackTrace();
        }

        return userDataList;
    }

    public boolean checkCredentials(String username, String password) {
        if (userDatabase == null) {
            System.out.println("User database is not loaded.");
            return false;
        }

        // Check if the provided username exists in the database
        if (users.containsKey(username)) {
            // Compare the provided password with the stored password
            String storedPassword = users.get(username);
            return storedPassword.equals(password);
        }

        // Username not found in the database
        return false;
    }

    public boolean loadUserData() {
        String filePath = "user_database.ser";
        Path databaseFilePath = Paths.get(filePath);
        if (Files.exists(databaseFilePath)) {
            try (FileInputStream fileIn = new FileInputStream(filePath); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

                userDatabase = (UserInfo) objectIn.readObject();
                users = userDatabase.getUsers();
                return true;
            } catch (IOException | ClassNotFoundException e) {
                // Log the error
                errorLogger.severe("Failed to load user database: " + e.getMessage());
                return false;
            }
        } else {
            System.err.println("user_database.ser file does not exist.");
            return false;
        }
    }
}
