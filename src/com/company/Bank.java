package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Bank {
    BankAccountArrayDB db = new BankAccountArrayDB();
    public static String[] currentUser = new String[1];

    //verifies user account username and password
    public void verifyAccount(){
        Scanner sn = new Scanner(System.in);
        System.out.println("account number (4 digit number)");
        String number = sn.nextLine();
        System.out.println("Password");
        String pwd = sn.nextLine();

        try {
            //check input
            for (int i = 0; i < db.account.length; i++) {
                if (number.isEmpty() || pwd.isEmpty()) {
                    System.out.println("\nIncorrect account number or password, please try again");
                    verifyAccount();
                }
                if (db.account[i][0].equals(number)) {
                    //fixed column index to check correct data
                    if (db.account[i][4].equals(pwd)) {
                        //store current user account number
                        currentUser[0] = number;
                        //return to mainpage method in Main class
                        Main main = new Main();
                        main.mainPage();
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("\nIncorrect account number or password, please try again");
            verifyAccount();
        }
    }

    //Creates new account with unique account number
    public void createAccount(){
        String accountNumber = getAccountNumber();
        //print new account number
        System.out.println("\nThis is your new account number " + accountNumber);
        System.out.println("Enter required information below");
        //Get input of users first and last name
        Scanner sn = new Scanner(System.in);

        //checks if user entered first name (cannot be empty)
        String firstName = "";
        boolean nameIsEmpty = true;
        while (nameIsEmpty) {
            System.out.println("First name");
            firstName = sn.nextLine();

            if(firstName.isEmpty()){
                System.out.println("Please enter your first name");
            }
            else{
                nameIsEmpty = false;
            }
        }

        //get last name (can be empty)
        System.out.println("Last name");
        String lastName = sn.nextLine();

        String password = "";
        boolean isTrue = true;
        while(isTrue) {
            //get input of account password
            System.out.println("Password");
            password = sn.nextLine();
            //check if user used their name as password
            if(password.equals(firstName)){
                System.out.println("Please do not use your name as password");
            }

            //checks if user has last name
            else if (!lastName.isEmpty() && password.equals(lastName)) {
                    System.out.println("Please do not use your name as password");
            }

            //check for empty input
            else if (password.isEmpty()) {
                System.out.println("Please enter a password");
            }
            else {
                isTrue = false;
            }
        }

        //store new account info in account array
        //search for empty row and column
        for (int i = 0; i < db.account.length; i++) {
            if(db.account[i][0] == null) {
                db.account[i][0] = accountNumber;
                db.account[i][1] = firstName;
                db.account[i][2] = lastName;
                db.account[i][3] = "0";
                db.account[i][4] = password;
                break;
            }
        }

        //Display username and password
        System.out.println("\nYour new account login details are displayed below, Please store it in a safe place: \nusername: "
                + accountNumber + "\npassword: " + password);
        //store current user account number
        currentUser[0] = accountNumber;
        //get users respond
        userRespond();
    }

    //generates and returns random account number
    public String getAccountNumber(){
        //generate 4 digit number
        Random rand = new Random();
        String accountNumber = "";
        while(accountNumber.length() < 4){
            int rand_num = rand.nextInt(10);
            String num = Integer.toString(rand_num);
            accountNumber += num;
        }

        //check if new account number already exist
//        for (int i = 0; i < db.account.length; i++) {
//            if (db.account[i][0].equals(accountNumber)) {
//                createAccount();
//            }
//        }
        return accountNumber;
    }

    //check user respond
    public void userRespond(){
        System.out.println("\nPlease enter 'Y' to continue or enter 'N' to get new account number");
        Scanner sn = new Scanner(System.in);
        String temp = sn.nextLine();

        if(temp.equals("Y") || temp.equals("y")){
            //return to mainPage method in Main class
            Main main = new Main();
            main.mainPage();
        }
        else if(temp.equals("N") || temp.equals("n")){
            //delete previously created account
            //search previous account using account number

            createAccount();
        }
        else{
            userRespond();
        }
    }
}
