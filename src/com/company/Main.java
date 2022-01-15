package com.company;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final ArrayList<String> books = new ArrayList<>();
    private static final File logins = new File("userLogins.txt");

    public static void main(String[] args) {
        boolean quit = false;
        CreateFile();
        getLoginDetails();
        while (!quit) {
            if (nextBook().equals("n")) {
                quit = true;
            } else {
                books.add(getBookDetails());
            }
        }

        getBookData(0);
    }

    public static String getBookDetails() {

        String bookTitle = getInput("Please enter the book title");
        int ISBN = getIsbn();
        String authorName = getInput("Please enter the author");
        String bookGenre = getInput("Please enter the book genre");
        return (bookTitle + "," + ISBN + "," + authorName + "," + bookGenre);
    }

    private static int getIsbn() {

        int ISBN;
        while (true) {
            try {
                ISBN = Integer.parseInt(getInput("Please enter the ISBN of the book"));
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
        }
        return ISBN;
    }

    public static void getLoginDetails() {
        String getUsername = getInput("username? ");
        String getPassword = getInput("password? ");
        String userData = getUsername + "," + getPassword;
        if (!checkLogin(userData)) {
            registerUser();
        }
    }


    public static boolean checkLogin(String userData) {
        boolean registeredUser = false;
        try {
            Scanner myReader = new Scanner(logins);
            while (!registeredUser) {
                String data;
                try {
                    data = myReader.nextLine();
                } catch (Exception e) {
                    return false;
                }
                if (data.equals(userData)) {
                    registeredUser = true;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("you already have an account");
        return registeredUser;
    }

    public static String getInput(String prompt) {

        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }


    public static void getBookData(int dataPart) {

        for (String book : books) {
            String[] bookDetails = book.split(",");
            System.out.println(bookDetails[dataPart]);
        }
    }


    public static String nextBook() {

        String enterBook;
        do {
            enterBook = getInput("Would you like to add another book? PLease enter y or n: ");
        } while (!enterBook.equals("y") && (!enterBook.equals("n")));
        return enterBook;
    }

    public static void CreateFile() {
        try {
            if (logins.createNewFile()) {
                System.out.println("File created: " + logins.getName());
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void registerUser() {
        System.out.println("You are registering now as you did not have login details in our database");
        String getUsername = getInput("Please enter your username ");
        String getPassword = getInput("Please enter your password: ");
        String userData = getUsername + "," + getPassword;
        System.out.println(userData);
        WriteToFile(userData);
    }

    public static void WriteToFile(String userData) {
        try {
            FileWriter myWriter = new FileWriter(logins.getName(), true);
            myWriter.write(userData + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}