/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;

/**
 *  This class manages functions that only the Owner is allowed to execute, such as adding and removing books and customers
 * 
 */
public class Owner extends State{
    protected ArrayList<Book> books = BookManager.getBooks();             // Retrieve list of Books from books.txt
    protected ArrayList<Customer> customers = UserManager.getCustomers(); // Retrieve list of Customers from customers.txt
//    private final String path = "C:\\Users\\"; Dont
    
    public void addBook(String bookName, double bookPrice) {
        // Check if book already exists
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equals(bookName)) {
                System.out.println("Book already exists."); // Print error
                return;
            }
        }
        books.add(new Book(bookName, bookPrice));
    }
    
    public void removeBook(String bookName) {
        // Look for book in the system
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equals(bookName)) {
                books.remove(i);
                return;
            }
        }
        System.out.println("Book doesn't exist."); // If we reach this point and we haven't removed the book, notify user
    }
    
    public void addCustomer(String username, String password) {
        // Check if customer is already exists
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(username)) {
                System.out.println("Customer already exists."); // Print error
                return;
            }
        }
        customers.add(new Customer(username, password));
    }
    
    public void removeCustomer(String username) {
        // Look for customer in the system
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(username)) {
                customers.remove(i);
                return;
            }
        }
        System.out.println("Customer doesn't exist."); // If we reach this point and we haven't removed the customer, notify user
    }
}
