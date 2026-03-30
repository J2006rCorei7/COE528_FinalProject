/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

import java.util.ArrayList;
import java.util.Iterator;

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
        ArrayList<Book> books = BookManager.getBooks();

        for (Book book : books) {
            if (book.getName().trim().equalsIgnoreCase(bookName.trim())) {
                System.out.println("Book already exists.");
                return false;
            }
        }

        books.add(new Book(bookName.trim(), bookPrice));
        BookManager.saveData(books);
        return true;
    }
    
    /**
    *    Removes a book from the collection.
    *    @param bookName the name of the book to be removed
    *    @return true if the removal was successful, false if the book doesn't exist
    */
    public static boolean removeBook(String bookName) {
        ArrayList<Book> books = BookManager.getBooks();
        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getName().trim().equalsIgnoreCase(bookName.trim())) {
                iterator.remove();
                BookManager.saveData(books);
                return true;
            }
        }

        System.out.println("Book doesn't exist.");
        return false;
    }
    
    /**
    *    Removes a book from the collection.
    *    @param bookObj the name of the book to be removed
    *    @return true if the removal was successful, false if the book doesn't exist
    */
    public static boolean removeBook(Book bookObj) {
        ArrayList<Book> books = BookManager.getBooks();
        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getName().trim().equalsIgnoreCase(bookObj.getName().trim())
                    && book.getPrice() == bookObj.getPrice()) {
                iterator.remove();
                BookManager.saveData(books);
                return true;
            }
        }

        System.out.println("Book doesn't exist.");
        return false;
    }
    
    /**
    *    Adds a new customer to the system.
    *    @param username the username of the customer to be added
    *    @param password the password of the customer to be added
    *    @return true if the addition was successful, false if the username already exists
    */
    public static boolean addCustomer(String username, String password) {
        ArrayList<Customer> customers = UserManager.getCustomers();

        for (Customer customer : customers) {
            if (customer.getName().trim().equalsIgnoreCase(username.trim())) {
                System.out.println("Customer already exists.");
                return false;
            }
        }

        customers.add(new Customer(username.trim(), password));
        UserManager.saveData(customers);
        return true;
    }
    
    /**
    *    Removes a customer from the system.
    *    @param username the username of the book to be removed
    *    @return true if the removal was successful, false if the customer doesn't exist
    */
    public static boolean removeCustomer(String username) {
        ArrayList<Customer> customers = UserManager.getCustomers();
        Iterator<Customer> iterator = customers.iterator();

        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getName().trim().equalsIgnoreCase(username.trim())) {
                iterator.remove();
                UserManager.saveData(customers);
                return true;
            }
        }

        System.out.println("Customer doesn't exist.");
        return false;
    }
}
