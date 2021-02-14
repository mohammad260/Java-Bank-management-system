package com.company;

import java.util.Scanner;

public class Main {
    private static int num;

    public static void main(String[] args) {
        Main main = new Main();
        //call to store into array
        BankAccountArrayDB db = new BankAccountArrayDB();
        db.SampleAccounts();
        main.loginPage();
    }

    public void loginPage(){
        printDash();
        System.out.println("\n1 - Log in");
        System.out.println("2 - Create new account");
        System.out.println("3 - Log in as admin");

        getInput();
        checkInput();
    }

    public void getInput(){
        Scanner sn = new Scanner(System.in);
        System.out.println("\nPlease enter one of the above number");
        num = sn.nextInt();
    }

    public void checkInput(){
        //Instance of Bank class
        Bank bank = new Bank();
        Admin admin = new Admin();

        //check input and call method
        if (num == 1) {
            bank.verifyAccount();
        } else if (num == 2) {
            bank.createAccount();
        } else if (num == 3) {
            admin.checkLogin();
        }else{
            getInput();
            checkInput();
        }
    }

    //Main page
    public void mainPage(){
        printDash();
        System.out.println("\n1 - View balance");
        System.out.println("2 - Withdraw cash");
        System.out.println("3 - insert cash");
        System.out.println("4 - Pay someone");
        //return to start page
        System.out.println("5 - Sign out");
        printDash();
        mainPageInput();
    }

    public void mainPageInput(){
        Customer customer = new Customer();
        System.out.println("\nPlease enter one of the above number");
        Scanner sn = new Scanner(System.in);
        int num = sn.nextInt();

        switch (num) {
            case 1:
                //System.out.println(customer.viewBalance());
                System.out.println("Balance: " + customer.viewBalance());
                askToContinue();
                mainPage();
                break;
            case 2:
                customer.withdraw();
                mainPage();
                break;
            case 3:
                customer.addBalance();
                mainPage();
                break;
            case 4:
                customer.paySomeone();
                mainPage();
                break;
            case 5:
                loginPage();
            default:
               mainPage();
        }
    }

    //Prints multiple '-' in a single line
    public void printDash(){
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
    }

    public void askToContinue(){
        Scanner sn = new Scanner(System.in);
        System.out.println("Press Enter/return key to continue");
        String key = sn.nextLine();
    }
}