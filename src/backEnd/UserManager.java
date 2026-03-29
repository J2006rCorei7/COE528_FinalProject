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

/*
    This class manages reading and writing to the customers.txt file. It can retrieve the current data and it can also save the current data.
    This class also handles login.
*/
public class UserManager {
    private static final String filePath = "src/backEnd/customers.txt"; // To be edited for Demo
    
    // Owner credentials
    private static final String ownerUsername = "admin";
    private static final String ownerPassword = "admin";
    
    private static Customer currentCustomer;
    
    /**
    * Reads customer data from a file and returns a list of Customer objects.
    * Each line in the file is expected to contain customer information separated by tabs.
    *
    * EFFECTS: Processes the file line by line, creating Customer objects for valid entries.
    *          Lines with missing or extra data are skipped.
    *
    * @return an ArrayList containing all valid Customer objects read from the file
    */
    public static ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCounter = 1; // Debugging tool
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                
                // If we're missing data or have extra data, skip this line
                if (parts.length != 3) { 
                    System.out.println("Error extracting customers: missing or extra data on line: " + lineCounter);
                    continue; 
                }
                
                // Turn parts of the line into individual variables
                String username = parts[0].trim();
                String password = parts[1].trim();
                int points = Integer.parseInt(parts[2].trim()); 

                // Add new Book object to the ArrayList
                customers.add(new Customer(username, password, points));
                lineCounter++;
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } 
        catch (IOException e) {
            System.out.println("IO Error");
        }
        
        return customers;
    }
    
    // EFFECTS: Overwrites entire books.txt file with new data
    protected static void saveData(ArrayList<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customers) {
                String line = customer.getName() + "\t" + customer.getPassword() + "\t" + customer.getPoints(); // Construct line to write
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Error saving customer data: IO Error");
        }
    }
    
    // EFFECTS: Authorizes username and input; returns 1 for Owner, returns 0 for Customer, and returns -1 for error
    public static int login(String username, char[] c_password) {
        String password = new String(c_password); // Convert char[] password into String password
        
        if (username.equals(ownerUsername) && password.equals(ownerPassword)) {
            System.out.println("Owner login successful.");
            return 1;
        }
        for (Customer customer : getCustomers()) {
            if (customer.getName().equals(username) && customer.getPassword().equals(password)) {
                System.out.println("Customer login successful.");
                currentCustomer = customer;
                return 0;
            }
        }
        System.out.println("Error logging in: incorrect username or password.");
        return -1;
    }
    
    public static void logout() {
        currentCustomer = new Customer("", "", 0);
    }
    
    // ACCESS: Login Panel 
    public static Customer getCustomer() {
        return currentCustomer;
    }
}
