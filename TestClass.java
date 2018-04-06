
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ulusoy
 */
public class TestClass {

    public static void main(String[] args) throws IOException { // This "throws IOException" is for 120th line.
        Scanner scn = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();

        // Load a dictionary from a given text file part:
        // This code block is from CSE112 notes.
        System.out.print("Please enter dictionary file name to start: \n(example: dict.txt) ");
        String filename = scn.next();
        try {
            File file = new File(filename);
            if (file.exists()) {
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String line = bf.readLine();
                while (line != null) {
                    dictionary.insert(line.trim());
                    line = bf.readLine();
                }
                System.out.println(filename + " is loaded :-)");
            } else {
                System.out.println("Couldn't find dictionary file as " + filename);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't find dictionary file as " + filename);
        } catch (IOException ex) {
            System.out.println("Couldn't read dictionary file");
        }

        System.out.println("\n" + "Select a function given menu: \n"
                + "1. Search for an entry in this dictionary \n"
                + "2. Insert a word to the dictionary \n"
                + "3. Delete a word from the dictionary \n"
                + "4. Do a spell check \n"
                + "5. Set the size \n"
                + "6. Save and/or quit \n"
                + "7. Exit");

        System.out.print("\n" + "Your choice: ");
        int option = scn.nextInt();

        while (option >= 1 || option <= 6) {

            switch (option) {
                case 1:
                    // Search:
                    System.out.print("Enter word to search: ");
                    String str = scn.next();
                    boolean found = dictionary.search(str);
                    if (found) {
                        System.out.println("Found word: " + str);
                    } else {
                        System.out.println("Didn't find word in the dictionary: " + str);
                    }

                    break;
                case 2:
                    // Insert:
                    System.out.print("Enter word to insert: ");
                    str = scn.next();
                    dictionary.insert(str);
                    System.out.println("Inserted word into the dictionary: " + str);
                    break;
                case 3:
                    // Delete:
                    System.out.print("Enter word to delete: ");
                    str = scn.next();
                    found = dictionary.delete(str);
                    if (found) {
                        System.out.println("Deleted word from the dictionary: " + str);
                    } else {
                        System.out.println("Didn't find word in the dictionary: " + str);
                    }
                    break;
                case 4:
                    // SpellCheck:
                    System.out.print("Enter test file name: \n(example: dict.txt) ");
                    filename = scn.next();
                    try {
                        File file = new File(filename);
                        if (file.exists()) {
                            BufferedReader bf = new BufferedReader(new FileReader(file));

                            String line = bf.readLine();
                            while (line != null) {
                                line = line.trim().toLowerCase(); // trim is for spaces and toLowerCase is for capital chars.
                                if (!dictionary.search(line)) {
                                    System.out.println(line + " is misspelled");
                                }
                                line = bf.readLine();
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        System.out.println("Couldn't find test file as " + filename);
                    } catch (IOException ex) {
                        System.out.println("Couldn't read test file as " + filename);
                    }
                    break;
                case 5:
                    // Change to hash table's size: 
                    // Note: I added this (set the size) menu, it does not exist at PDF.
                    System.out.print("Enter new size for the table: ");
                    int newSize = scn.nextInt();
                    dictionary.setSize(newSize);
                    System.out.println("Size is " + dictionary.getSize());
                    break;
                case 6:
                    // Save: 
                    // Note: This part is for save, but ıf the user wants to leave, she can exit
                    System.out.print("File name: \n(example: saved file.txt) ");
                    filename = scn.next();
                    dictionary.save(filename);
                    System.out.println("\nIf you want to leave, please press 1 otherwise 0");
                    int choice = scn.nextInt();
                    if (choice == 1) {
                        System.out.println("\nYou are leaving... \n" + "You have left :-(");
                        return;
                    } else {
                        break;
                    }
                case 7:
                    // Exit:
                    System.out.println("You are leaving... \n" + "You have left :-(");
                    return;
                default:
                    System.out.println("Wrong option. Select again: ");

            }
            System.out.println("\n" + "Select a function given menu: \n"
                    + "1. Search for an entry in this dictionary \n"
                    + "2. Insert a word to the dictionary \n"
                    + "3. Delete a word from the dictionary \n"
                    + "4. Do a spell check \n"
                    + "5. Set the size \n"
                    + "6. Save and/or quit \n"
                    + "7. Exit");

            System.out.print("\n" + "Your new choice: ");
            option = scn.nextInt();
        }
    }
}
