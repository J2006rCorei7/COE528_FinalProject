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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karlh
 */
public class Books {
   
    private final String path = "C:\\coe528\\";
    private final String filename = "books.txt";
    public Books(String name, double price){
        
    }
    
    protected String[][] getList(){
        try (BufferedReader br = new BufferedReader(new FileReader(path+filename)){
            //String line;
            while ((line = br.readLine()) != null){
            
            }
        }
        
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Books.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    protected static boolean add(){
        try (FileWriter fileWrite = new FileWriter(path + filename, true)) {
            fileWrite.append(msg);
            fileWrite.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
    
    protected static boolean remove(){
        
        return false;
    }
    
}
