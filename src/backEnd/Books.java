/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author karlh
 */
public class Books {
    private static final String filepath = "C:\\coe528\\books.txt";
    

    
    protected static String[][] getList() throws FileNotFoundException, IOException{
        ArrayList<String[]> temp = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;
            //Splitting name and price and adding to temp ArrayList
            br.readLine();
            while((line = br.readLine()) != null){
                String[] parts = line.split("\t");
                temp.add(parts);
            }
            br.close();
        }catch (FileNotFoundException e){
            System.out.println("Could not find file");
        }catch (IOException e){
            System.out.println("IO Error");
        }
        //Converting temp ArrayList to 2d array
        //NOTE: Can be changed later to return ArrayList, will need to change use methods
        String[][] books = new String[temp.size()][2];
        for (int i = 0; i < temp.size(); i++) {
            books[i] = temp.get(i);
        }
        return books;
    }
    
    protected static boolean add(String name, double price) throws FileNotFoundException, IOException{
        String[][] books = getList();
        
        //Check if name already exists
        for (String[] book : books) {
            if(book[0].toLowerCase().equals(name.toLowerCase())){
                System.out.println("Name already exists");
                return false;
            }
        }
        //Append Book to EOL
        try (FileWriter fileWrite = new FileWriter(filepath, true)) {
            fileWrite.append("\n"+name + "\t" + price);
            fileWrite.close();
        }catch (IOException e) {
            System.out.println("IO Error");
            return false;
        }
        
        return true;
    }
    
    protected static boolean remove(String name) throws FileNotFoundException, IOException{
        String[][] books = getList();
        int row = -1;
        //Check if name exists
        for (int i=0; i<books.length;i++) {
            if(books[i][0].toLowerCase().equals(name.toLowerCase())){
                row = i;
            }
        }
        if(row==-1){
            System.out.println("Name does not exist");
            return false;
        }
        try (FileWriter fileWrite = new FileWriter(filepath)){
            fileWrite.write("Name:\tPrice:");
            for (int i=0; i<books.length;i++){
                if (i!=row){
                    fileWrite.append("\n" + books[i][0] + "\t" + books[i][1]);
                }
            }
            fileWrite.close();
        }
        
        
        return true;
    }
    
    
    
    /**
     * Temp Main
     * @param args
     * @throws java.io.IOException   */
    
    
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
    
}
