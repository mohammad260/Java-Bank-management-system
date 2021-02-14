package com.company;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.Scanner;

public class Customer {
    BankAccountArrayDB db = new BankAccountArrayDB();
    Main main = new Main();
    //get current user account number from bank class
    Bank bank = new Bank();
    String currentUser = bank.currentUser[0];

    public String viewBalance(){
        String balance = "";
        for (int i = 0; i < db.account.length; i++) {
            if(db.account[i][0].equals(currentUser)){
                balance = db.account[i][3];
                break;
            }
        }
        return balance;
    }

    public void paySomeone(){
        Scanner sn = new Scanner(System.in);
        System.out.println("Recipient account number");
        String recipientNumber = sn.nextLine();
        boolean isTrue = true;

        try {
            for (int i = 0; i < db.account.length; i++) {
                if (db.account[i][0].equals(recipientNumber)) {
                    System.out.println("Enter amount");
                    String amount = sn.nextLine();
                    //check if amount is number

                    //check if amount is more then balance

                    while(isTrue) {
                        System.out.println("Would you like to continue with the transfer");
                        if (getAnswer()) {
                            String newRecipientBalance = transferMoney(db.account[i][3], amount);
                            db.account[i][3] = newRecipientBalance;

                            //reduce amount from senders account
                            reduceBalance(currentUser, amount);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("account not found, would you like to try again");
            if (getAnswer()) {
                paySomeone();
            }
        }
    }

    //get y or n input from user
    public boolean getAnswer(){
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter 'y' to continue or 'N' to cancel");
        String input = sn.nextLine();

        if(input.equals("Y") || input.equals("y")){
           return true;
        }
        else if(input.equals("N") || input.equals("n")){
           return false;
        }
        else{
            getAnswer();
        }
        return false;
    }

    //adds amount to account balance
    public String transferMoney(String recipientBalance, String amount){
        //convert to int to added together
        int tempRecipientBalance = Integer.parseInt(recipientBalance);
        int tempAmount = Integer.parseInt(amount);

        //add sum to newBalance and return it
        int num = tempRecipientBalance + tempAmount;
        String newBalance = Integer.toString(num);

        return newBalance;
    }

    public void reduceBalance(String accountNumber, String amount){
        //subtract money from balance
        String newAccountBalance;
        for (int i = 0; i < db.account.length; i++) {
            if(db.account[i][0].equals(accountNumber)){
                int balance = Integer.parseInt(db.account[i][3]);
                int tempAmount = Integer.parseInt(amount);
                int newBalance = balance - tempAmount;

                //store new balance
                newAccountBalance = Integer.toString(newBalance);
                db.account[i][3] = newAccountBalance;
                main.mainPage();
            }
        }
    }

    public void increaseBalance(String accountNumber, String amount){
        //add money to balance
        String newAccountBalance = "";
        for (int i = 0; i < db.account.length; i++) {
            if(db.account[i][0].equals(accountNumber)){
                int balance = Integer.parseInt(db.account[i][3]);
                int tempAmount = Integer.parseInt(amount);
                int newBalance = balance + tempAmount;

                //store new balance
                newAccountBalance = Integer.toString(newBalance);
                db.account[i][3] = newAccountBalance;
                main.mainPage();
            }
        }
    }

    public void withdraw(){
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter withdrawal amount");
        String amount = sn.nextLine();

        //call reduceBalance to subtract balance with withdrawal amount
        reduceBalance(currentUser, amount);
    }

    public void addBalance(){
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter withdrawal amount");
        String amount = sn.nextLine();

        //call increaseBalance function to add amount to account balance
        increaseBalance(currentUser, amount);
    }
}
