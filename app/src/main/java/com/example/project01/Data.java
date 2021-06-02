package com.example.project01;

import java.io.Serializable;
import java.util.HashMap;

public class Data implements Serializable {

    // A hashmap data structure for holding usernames and passwords pair
    private static HashMap<String, String> hmCredentials = new HashMap<>();

    public Data() {
        // Adding some items into the hashmap table
        hmCredentials.put("AJ", "CoolDude1");
        hmCredentials.put("test", "1234");
    }

    // This method adds a new username and password to the hashmap
    public void AddCredential(String username, String password) {
        hmCredentials.put(username, password);
    }

    // This method checks if username exists in the hashmap
    //Returns true if exists, else returns false
    public Boolean CheckUsername(String username) {
        Boolean retval = true;
        // Write your code here
        retval = hmCredentials.get(username) != null;
        return retval;
    }

    // This method checks a username and password combination is correct!
    //Returns true if correct, else false.
    public Boolean CheckCredentials(String username, String Password) {
        Boolean retval = true;
        // Write your code here

        if (CheckUsername(username) && hmCredentials.get(username).equals(Password)) {
            retval = true;
        } else {
            retval = false;
        }
        return retval;
    }

}
