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

public abstract class AccountBase {

    protected int accountBal;

    public AccountBase() {
        accountBal = 100;
    }

    protected int getBalance() {
        return accountBal;
    }

    protected void setBalance(int newBalance) {
        if(newBalance >= 0)
            this.accountBal = newBalance;
    }

    protected void depositMoney(int amount) {
        accountBal += amount;
    }

    protected void withdrawAmount(int amount) {
        if(amount <= accountBal)
            accountBal -= amount;
    }

    public abstract void ePurchase(int amount);
    public abstract String getAccountLevel();

    @Override
    public String toString() {
        return "Balance: $ " + accountBal;
    }

    public boolean RepOK() {
        return this.accountBal >= 0;
    }
}

