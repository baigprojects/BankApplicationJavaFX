package coe528.project;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Overview: Customer class represents a customer in a banking app, managing their login,
 * connected bank account, and their role
 * This class is mutable.
 * 
 * (AF(c): A customer c is represented as multiple elements
 * where 'user' is the username, 'pass' is the password, 'account' is
 * the bank account connected with the customer, and 'role' is "customer". 
 * 
 *  RI(c): true if user != null && !user.isEmpty() && pass != null && !pass.isEmpty() && account != null && role.equals("customer"), false otherwise.
 */
public class Customer {

    private String user;
    private String pass;
    private AccountBase account;
    private String role = "customer";

    /**
     * Effects: creates a new Customer object with a specified username and password.
     * and initializes a new account file if it does not exist, setting the initial account balance
     * to 100 and the account level to SilverLevel.
 
     */
    public Customer(String user, String pass) {
        try {
            File accountInfo = new File(user + ".txt");
            if (accountInfo.createNewFile()) {
                System.out.println("New User: " + accountInfo.getName());
                this.user = user;
                this.pass = pass;
                account = new SilverLevel(); 
                FileWriter writeToFile = new FileWriter(user + ".txt");
                writeToFile.write(user + "\n" + pass + "\n" + "100"); 
                writeToFile.close();
            } else {
                this.user = user;
                this.pass = pass;
                account = new SilverLevel(); 
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    /**
     * Effects: Returns the current balance of the customer's account.
     */
    public int getBalance() {
        return account.getBalance();
    }

    /**
     * Modifies: this.account
     * Effects: Deposits the specified amount into the customer's account and updates the account 
     * level based on the new balance.
     */
    public void depositMoney(int amount) {
        account.depositMoney(amount);
        setAccountLevel(); 
    }

    /**
     * Modifies: this.account
     * Requires: The account balance is greater than or equal to the withdrawal amount.
     * Effects: Withdraws the specified amount from the customer's account if funds are sufficient,
     * and updates the account level based on the new balance. Displays an error if funds are insufficient.
     */
    public void withdrawMoney(int amount) {
        if (account.getBalance() >= amount) {
            account.withdrawAmount(amount);
            setAccountLevel(); 
        } else {
            NotificationWindow.display("Error", "Insufficient funds");
        }
    }

    /**
     * Modifies: this.account
     * Effects: Makes an online purchase of the specified amount from the customer's account, 
     * applying any relevant fees based on the account level, and updates the account level based on the new balance.
     */
    public void ePurchase(int amount) {
        account.ePurchase(amount);
        setAccountLevel();
    }

    /**
     * Modifies: this.account
     * Effects: Updates the account level based on the current balance, upgrading or downgrading
     */
    public void setAccountLevel() {
        int balance = account.getBalance();
        if (balance < 10000) {
            if (!(account instanceof SilverLevel)) {
                account = new SilverLevel();
            }
        } else if (balance < 20000) {
            if (!(account instanceof GoldLevel)) {
                account = new GoldLevel();
            }
        } else {
            if (!(account instanceof PlatinumLevel)) {
                account = new PlatinumLevel();
            }
        }
        account.setBalance(balance); 
    }

    /**
     * Effects: Returns the account level as a string
     */
    public String getAccountLevel() {
    return account.getAccountLevel();
    }
    /**
 * Effects: Returns the username of the customer
 */
public String getUsername() {
    return user;
}

/**
 * Effects: Returns the password of the customer
 */
public String getPassword() {
    return pass;
}

/**
 * Returns the state of the customer (username, account balance, and account level)
 */
@Override
public String toString() {
    return String.format("User: %s, Balance: %d, Level: %s", user, getBalance(), getAccountLevel());
}

/**
 * Checks if the representation invariant holds for this object.
 * returns true if the invariant holds, otherwise its false
 */
public boolean repOk() {
    return user != null && !user.isEmpty() && pass != null && !pass.isEmpty() && account != null && role.equals("customer");
}
}
