/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *  This class manages reading and writing to the books.txt file. It can retrieve the current data and it can also save the current data.
 * 
 */
public class BookManager {
    private static final String filePath = "src/backEnd/books.txt"; // To be edited for Demo
    
    private static ArrayList<Book> shoppingCart;
    
    // EFFECTS: Reads books.txt line by line and returns an ArrayList of Book objects
    public static ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCounter = 1; // Debugging tool
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                
                // If we're missing data or have extra data, skip this line
                if (parts.length != 2) { 
                    System.out.println("Error extracting books: missing or extra data on line: " + lineCounter);
                    continue; 
                }
                
                // Turn parts of the line into individual variables
                String bookName = parts[0].trim();
                double bookPrice = Double.parseDouble(parts[1].trim());

                // Add new Book object to the ArrayList
                books.add(new Book(bookName, bookPrice));
                lineCounter++;
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } 
        catch (IOException e) {
            System.out.println("IO Error");
        }
        
        return books;
    }
    
    public static ArrayList<Book> getBooks(String[] bookNames) {
        ArrayList<Book> selectedBooks = new ArrayList<>();
        
        for (Book book : getBooks()) {
            for (String selectedBook : bookNames) {
                if (book.getName().equals(selectedBook)) {
                    selectedBooks.add(book);
                }
            }
        }
        
        return selectedBooks;
    }
    
    public static void setShoppingCart(String[] bookNames) {
        shoppingCart = getBooks(bookNames);
    }
    
    public ArrayList<Book> getShoppingCart() {
        return shoppingCart;
    }
    
    public void emptyShoppingCart() {
        for (Book book : shoppingCart) {
            Owner.removeBook(book);
        }
    }
    
    // EFFECTS: Overwrites entire books.txt file with new data
    protected static void saveData(ArrayList<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                String line = book.getName() + "\t" + String.format("%.2f", book.getPrice()); // Construct line to write
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Error saving book data: IO Error");
        }
    }
}
