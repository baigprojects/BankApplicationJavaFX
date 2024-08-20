
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

public class SilverLevel extends AccountBase {

    @Override
    public void ePurchase(int amount) {
        if (accountBal >= amount + 20) {
            accountBal -= (amount + 20);
            NotificationWindow.display("Purchase Confirmation", "Silver level purchase of $" + amount + " with $20 fee.");
        } else {
            NotificationWindow.display("Purchase Error", "Insufficient funds for fee.");
        }
    }

    @Override
    public String getAccountLevel() {
        return "Silver Level";
    }
}




