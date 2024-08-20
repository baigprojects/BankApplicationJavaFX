
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Baig
 */

public class Manager {

    private String user = "admin";
    private String pass = "admin";
    private String role = "manager";

    public Manager() {
        try {
            File accountInfo = new File(user + ".txt");
            if (accountInfo.createNewFile()) {
                FileWriter writeToFile = new FileWriter(user + ".txt");
                writeToFile.write(user + "\n" + pass);
                writeToFile.close();
            }
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    public Customer addCustomer(String user, String pass) {
        return new Customer(user, pass);
    }

    public void deleteCustomer(Customer customerDelete) {
        File file = new File(customerDelete.getUsername() + ".txt");
        if (file.delete()) {
            NotificationWindow.display("Deleted:", "Customer " + customerDelete.getUsername() + " successfully deleted.");
        } else {
            NotificationWindow.display("Delete Error", "Cannot delete customer " + customerDelete.getUsername() + ".");
        }
    }
}

