package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Admin {
    private String username = "admin";
    private String password = "password";

    public void checkLogin(){
        printDash();
        Scanner sn = new Scanner(System.in);
        System.out.println("\nadmin username");
        String inputUsername = sn.nextLine();
        System.out.println("admin password");
        String pwd = sn.nextLine();

        try {
            //check input
            if(username.equals(inputUsername) && password.equals(pwd)) {
                System.out.println("login successful");
                adminManagement();
            }else{
                System.out.println("\nIncorrect username or password, please try again");
                checkLogin();
            }
        }
        catch(Exception e){
            System.out.println("\nIncorrect username or password, please try again");
            checkLogin();
        }
    }

    private void adminManagement(){
        printDash();
        System.out.println("\n1 - View bank account database");
        System.out.println("2 - Log out");

        Scanner sn = new Scanner(System.in);
        System.out.println("enter one of the above number");
        int input = sn.nextInt();
        getInput(input);
    }

    private void getInput(int num){
        Main main = new Main();
        switch (num){
            case 1 :
                viewDatabase();
                break;
            case 2 :
                main.loginPage();
                break;
            default:
                System.out.println("Incorrect input, please try again");
                adminManagement();
        }
    }

    private void viewDatabase(){
        BankAccountArrayDB db = new BankAccountArrayDB();
        //view database
        printDash();
        System.out.println("\n");
        for (String[] row: db.account
        ) {
            System.out.println(Arrays.toString(row));
        }
        askToContinue();
        adminManagement();
    }

    //Prints multiple '-' in a single line
    public void printDash(){
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
    }

    public void askToContinue(){
        Scanner sn = new Scanner(System.in);
        System.out.println("\nPress Enter/return key to continue");
        String key = sn.nextLine();
    }
}
