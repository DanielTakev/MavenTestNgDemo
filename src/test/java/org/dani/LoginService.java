package org.dani;

/**
 * A simple Service to demonstrate Data-Driven Testing logic.
 */
public class LoginService {

    /**
     * Simulates a login process with basic business rules.
     * @param username The username provided
     * @param password The password provided
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        // Rule 1: Admin always gets in with the right password
        if (username.equals("admin_user") && password.equals("pass123")) {
            return true;
        }

        // Rule 2: Specifically handle a locked user
        if (username.equals("locked_user")) {
            System.out.println("LOG: User " + username + " is currently locked.");
            return false;
        }

        // Rule 3: Fail for anything else (invalid credentials)
        return false;
    }
}
