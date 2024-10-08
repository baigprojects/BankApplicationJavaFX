
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

/**
 *
 * @author Baig
 */


public class PlatinumLevel extends AccountBase {

    @Override
    public void ePurchase(int amount) {
        if (accountBal >= amount) {
            accountBal -= amount;
            NotificationWindow.display("Purchase Completed", "Platinum level purchase of $" + amount + " without any fee.");
        } else {
            NotificationWindow.display("Purchase Error", "Insufficient funds for purchase.");
        }
    }

    @Override
    public String getAccountLevel() {
        return "Platinum Level";
    }
}

