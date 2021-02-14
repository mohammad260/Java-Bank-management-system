package com.company;

public class BankAccountArrayDB {
    public static String[][] account = new String[5][5];
    /* 2D array (DB) layout design
     * Each customers account details are stored in a single row and in multiple column. this will allow the database (array)
       to be cleaner and easy to understand.
     * | [0] | [1] | [2] | [3] | [4]
     * | Account number | First name | Last name | account balance | Password
     * Account number is used as username and password for account password
     */

    //Sample accounts stored in the DB
    public void SampleAccounts(){
        // Bruce account
        account[0][0] = "1";
        account[0][1] = "Bruce";
        account[0][2] = "Wayne";
        account[0][3] = "12000";
        account[0][4] = "a";

        //Tony account
        account[1][0] = "1122";
        account[1][1] = "Tony";
        account[1][2] = "Stark";
        account[1][3] = "22000";
        account[1][4] = "i";
    }
}
