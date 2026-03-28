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
 *
 * @author karlh
 */
public class BookManager {
    private static final String filepath = "C:\\coe528\\books.txt";
    
    // EFFECTS: Reads books.txt line by line and returns an ArrayList of Book objects
    protected static ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
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
    
    // EFFECTS: Overwrites entire books.txt file with new data
    protected static void saveData(ArrayList<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
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
    
//    protected static boolean add(String name, double price) throws FileNotFoundException, IOException{
//        //EFFECTS: Checks if name already exists, if not then appends book to EOF
//        //ACCESS: Owner only
//        String[][] books = getList();
//        
//        //Check if name already exists
//        for (String[] book : books) {
//            if(book[0].toLowerCase().equals(name.toLowerCase())){
//                System.out.println("Name already exists");
//                return false;
//            }
//        }
//        //Append Book to EOF
//        try (FileWriter fileWrite = new FileWriter(filepath, true)) {
//            fileWrite.append("\n" + name + "\t" + price);
//            fileWrite.close();
//        }catch (IOException e) {
//            System.out.println("IO Error");
//            return false;
//        }
//        return true;
//    }
//    
//    protected static boolean remove(String name) throws FileNotFoundException, IOException{
//        //EFFECTS: Checks if name exists, if so then rewrites the file without the book
//        //ACCESS: Owner only
//        String[][] books = getList();
//        int row = -1;
//        //Check if name exists
//        for (int i=0; i<books.length;i++) {
//            if(books[i][0].toLowerCase().equals(name.toLowerCase())){
//                row = i;
//                break;
//            }
//        }
//        
//        if(row==-1){
//            System.out.println("Name does not exist");
//            return false;
//        }
//        
//        try (FileWriter fileWrite = new FileWriter(filepath)){
//            fileWrite.write("Name:\tPrice:");
//            for (int i=0; i<books.length;i++){
//                if (i!=row){
//                    fileWrite.append("\n" + books[i][0] + "\t" + books[i][1]);
//                }
//            }
//            fileWrite.close();
//        }
//        return true;
//    }
    
    /**
     * Temp Main
     * @param args
     * @throws java.io.IOException   */
    
    /*
    public static void main(String args[]) throws IOException{
        add("Test", 16.32);
        add("Test2", 11);
        add("Test3", 13);
        add("Test 4", 11);
        
        
        String[][] books = getList();
        for (String[] b : books){
        System.out.println(b[0] + ", " + b[1]);
        }
        remove("Test2");
    }
    */
}
