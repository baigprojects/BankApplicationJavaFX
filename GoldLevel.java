

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

public class GoldLevel extends AccountBase {

    @Override
    public void ePurchase(int amount) {
        if (accountBal >= amount + 10) {
            accountBal -= (amount + 10);
            NotificationWindow.display("Purchase Completed", "Gold level purchase of $" + amount + " + $10 fee.");
        } else {
            NotificationWindow.display("Purchase Error", "Insufficient funds for purchase and fee.");
        }
    }

    @Override
    public String getAccountLevel() {
        return "Gold Level";
    }
}