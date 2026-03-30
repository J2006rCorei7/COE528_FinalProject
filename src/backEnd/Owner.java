/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

import java.util.ArrayList;

/**
 *  This class manages functions that only the Owner is allowed to execute, such as adding and removing books and customers
 * 
 */
public class Owner {
    protected static ArrayList<Book> books = BookManager.getBooks();             // Retrieve list of Books from books.txt
    protected static ArrayList<Customer> customers = UserManager.getCustomers(); // Retrieve list of Customers from customers.txt
    
    /**
    *    Adds a new book to the collection.
    *    @param bookName the name of the book to be added
    *    @param bookPrice the price of the book to be added
    *    @return true if the addition was successful, false if the book already exists
    */
    public static boolean addBook(String bookName, double bookPrice) {
        // Check if book already exists
        for (Book book : books) {
            if (book.getName().equals(bookName)) {
                System.out.println("Book already exists."); // Print error
                return false;
            }
        }
        books.add(new Book(bookName, bookPrice));
        BookManager.saveData(books);
        return true;
    }
    
    /**
    *    Removes a book from the collection.
    *    @param bookName the name of the book to be removed
    *    @return true if the removal was successful, false if the book doesn't exist
    */
    public static boolean removeBook(String bookName) {
        // Look for book in the system
        for (Book book : books) {
            if (book.getName().equals(bookName)) {
                books.remove(book);
                BookManager.saveData(books);
                return true;
            }
        }
        System.out.println("Book doesn't exist."); // If we reach this point and we haven't removed the book, notify user
        return false;
    }
    
    /**
    *    Removes a book from the collection.
    *    @param bookObj the name of the book to be removed
    *    @return true if the removal was successful, false if the book doesn't exist
    */
    public static boolean removeBook(Book bookObj) {
        // Look for book in the system
        for (Book book : books) {
            if (book.equals(bookObj)) {
                books.remove(bookObj);
                BookManager.saveData(books);
                return true;
            }
        }
        System.out.println("Book doesn't exist."); // If we reach this point and we haven't removed the book, notify user
        return false;
    }
    
    /**
    *    Adds a new customer to the system.
    *    @param username the username of the customer to be added
    *    @param password the password of the customer to be added
    *    @return true if the addition was successful, false if the username already exists
    */
    public static boolean addCustomer(String username, String password) {
        // Check if customer is already exists
        for (Customer customer : customers) {
            if (customer.getName().equals(username)) {
                System.out.println("Customer already exists."); // Print error
                return false;
            }
        }
        customers.add(new Customer(username, password));
        UserManager.saveData(customers);
        return true;
    }
    
    /**
    *    Removes a customer from the system.
    *    @param username the username of the book to be removed
    *    @return true if the removal was successful, false if the customer doesn't exist
    */
    public static boolean removeCustomer(String username) {
        // Look for customer in the system
        for (Customer customer : customers) {
            if (customer.getName().equals(username)) {
                customers.remove(customer);
                UserManager.saveData(customers);
                return true;
            }
        }
        System.out.println("Customer doesn't exist."); // If we reach this point and we haven't removed the customer, notify user
        return false;
    }
}
