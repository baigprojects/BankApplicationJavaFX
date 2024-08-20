
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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bank extends Application {
   

    private Button button;
    private Scene loginWindow, managerWindow,customerWindow;
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private static Manager admin = new Manager();
    private Customer tempCustomer;
    private int index = -1;
    
    

    public static void main(String[] args) {
               
         String currentDirectory = System.getProperty("user.dir"); 
         
         File dir = new File(currentDirectory);
         for(File file : dir.listFiles()){
             if(file.getName().endsWith((".txt"))){
                    try(Scanner readFile = new Scanner(file)){
                        String un = readFile.next();
                        String pw = readFile.next();
                        int balance = Integer.parseInt(readFile.next());
                    
                        customers.add(new Customer(un,pw));
                        for(Customer c : customers){
                            if(c.getUsername().equals(un))
                                c.depositMoney(balance - 100); 
                                c.setAccountLevel();
                        }
                    }
                    catch(Exception e3){}  
             }
         }
         
        launch(args);
        
    }

    
    @Override
    public void start(Stage window) throws Exception {
        
        window.setTitle("Bank Login");
        
        
        Label label1 = new Label("Username");
        Label label2 = new Label("Password");
        
        
        Label label3 = new Label("Add customer: Enter user and pass, then pres 'Create Customer' ");
        Label label4 = new Label("New Customer's username:");
        Label label5 = new Label("New Customer's password:");
        Label label6 = new Label("\n\n\n");
        Label label7 = new Label("Enter the user of the customer you want to delete:");
        Label label8 = new Label("\n\n\n");
        
        
        Label dLabel = new Label("\n\nEnter amount you want to deposit:");
        Label wLabel = new Label("\n\nEnter amount you want to withdraw:");
        Label opLabel = new Label("\n\nEnter cost of online purchase:");
        Label spaces = new Label("\n\n\n");
        
        
        TextField depositMoney = new TextField();
        TextField withdrawMoney = new TextField();
        TextField op = new TextField();
         
       
        Button getBalanceButton = new Button("View Account Balance");
        Button getCurrentLevel = new Button("Check Membership Level");
        Button doDepositAction = new Button("Deposit");
        Button doWithdrawAction = new Button("Withdraw");
        Button doOnlinePurchase = new Button("Complete Transaction");
        Button userLogout = new Button("Logout");
        
        
        TextField user = new TextField();
        TextField pass = new TextField();
        button = new Button("Login");
        
        
        Button createCustomer = new Button("Create Customer");
        Button deleteCustomer = new Button("Delete Customer");
        Button managerLogout = new Button("Logout");
        TextField createUsername = new TextField();
        TextField createPassword = new TextField();
        TextField userToDelete = new TextField();
        
        
        button.setOnAction(e ->{ 
               String inputtedFileName = user.getText();
               String inputtedPassword = pass.getText();
               File f = new File(inputtedFileName + ".txt");
               if(f.exists()) { 
                    try(Scanner readFile = new Scanner(f)){
                         String userOnFile = readFile.next();
                         String passOnFile = readFile.next();
                         
                     
                     
                     
                     
                    if(inputtedFileName.equals(userOnFile) && inputtedPassword.equals(passOnFile)){ 
                        if(userOnFile.equals("admin")){
                            window.setScene(managerWindow);
                            user.clear();
                            pass.clear();
                        }
                        
                        
                        else {  
                           for(int i = 0; i < customers.size(); i++){    
                                  if(customers.get(i).getUsername().equals(inputtedFileName)){
                                      tempCustomer = customers.get(i);
                                      index = i;
                                  }                        
                           }
                                      window.setScene(customerWindow);
                                      user.clear();
                                      pass.clear();
                          

                        } 
                        
  
                    }
                    else
                        NotificationWindow.display("Error", "Invalid user/pass.");
               
                    }  
                    catch(Exception e1){
                         System.out.println("Error");
                        }            
               } 
               
               else{
                   NotificationWindow.display("Error", "No such user exists.");
                           
               }
                                           
        });
        
        
        managerLogout.setOnAction(e -> {
          
            window.setScene(loginWindow);
            createUsername.clear();
            createPassword.clear();
            userToDelete.clear();
         
        } );
        
        
        userLogout.setOnAction(e -> { 
                        try{
                FileWriter writeToFile = new FileWriter(tempCustomer.getUsername() + ".txt");
                writeToFile.write(tempCustomer.getUsername() + "\n");
                writeToFile.write(tempCustomer.getPassword() + "\n");
                writeToFile.write(""+tempCustomer.getBalance());
                writeToFile.close();
            }
            catch(Exception w){}
            
            window.setScene(loginWindow);
            depositMoney.clear();
            withdrawMoney.clear();
            op.clear(); 
                });
        
        createCustomer.setOnAction(e -> {
                customers.add(new Customer(createUsername.getText(),createPassword.getText()));
                createUsername.clear();
                createPassword.clear();
                NotificationWindow.display("New Customer", "Customer created.");
                
                });
        
        deleteCustomer.setOnAction(e -> {
           String deleteThisUsername = userToDelete.getText();
           for(int i = 0; i < customers.size() ; i++){
               if(customers.get(i).getUsername().equals(deleteThisUsername)){
                   admin.deleteCustomer(customers.get(i));
                   customers.remove(i);
                   userToDelete.clear(); 
                   break;
               }
 
           }          
        });
        
        getBalanceButton.setOnAction(e ->{
            NotificationWindow.display("Balance", "Balance is $" +tempCustomer.getBalance());
        });
        
        
        getCurrentLevel.setOnAction(e -> {
            NotificationWindow.display("Balance", tempCustomer.getAccountLevel());
        });
       
        doDepositAction.setOnAction(e ->{
           
            try{
                int amount = Integer.parseInt(depositMoney.getText());
                tempCustomer.depositMoney(amount);
                depositMoney.clear();
            }
            catch(NumberFormatException e1){
                     NotificationWindow.display("Error", "Enter Integer.");  
                     depositMoney.clear();
            }      
        });
        
        doWithdrawAction.setOnAction(e -> {
            
            try{
                int amount = Integer.parseInt(withdrawMoney.getText());
                tempCustomer.withdrawMoney(amount);
                withdrawMoney.clear();
            }
            catch(NumberFormatException e1){
                     NotificationWindow.display("Error", "Enter integer.");  
                     withdrawMoney.clear();
            }      
        });
        
        doOnlinePurchase.setOnAction(e -> {
           
             try{
                int amount = Integer.parseInt(op.getText());
                tempCustomer.ePurchase(amount);
                op.clear();
            }
            catch(NumberFormatException e1){
                     NotificationWindow.display("Error", "Enter Integer.");
                     op.clear();
            }  
        });
        
                    
        
        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(20,20,20,20));
        layout1.getChildren().addAll(label1,user,label2,pass,button);
        
        loginWindow = new Scene(layout1,500,500);
       
          
        VBox layout2 = new VBox(10);
        layout2.setPadding(new Insets(20,20,20,20));
        layout2.getChildren().addAll(label3,label4,createUsername,label5,createPassword,createCustomer,label6,label7,userToDelete,deleteCustomer,label8,managerLogout);
        
        managerWindow = new Scene(layout2,500,700);
        
        
        VBox layout3 = new VBox(10);
        layout3.setPadding(new Insets(20,20,20,20));
        layout3.getChildren().addAll(getBalanceButton,getCurrentLevel,dLabel,depositMoney,doDepositAction,wLabel,withdrawMoney,doWithdrawAction,opLabel,op,doOnlinePurchase,spaces,userLogout);
        
        customerWindow = new Scene(layout3,500,700);
        
        window.setScene(loginWindow);
        window.show();
       
        
    }
}
