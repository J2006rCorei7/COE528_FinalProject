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
public class UserManager {
    private static final String filepath = "C:\\coe528\\userInfo.txt";
    private static final String ownerName = "Julian";
    private static final String ownerPassword = "badP4ssw0rd";
    
    protected static String[][] getLog() throws FileNotFoundException, IOException{
        //EFFECTS: reads userInfo.txt by line and sorts into a 2D ArrayList, which is typecasted and returned as String[][]
        ArrayList<String[]> temp = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            //Splitting name and price and adding to temp ArrayList
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                temp.add(parts);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
        //Converting temp ArrayList to 2d array
        //NOTE: Can be changed later to return ArrayList, will need to change use methods
        String[][] users = new String[temp.size()][3];
        for (int i = 0; i < temp.size(); i++) {
            users[i] = temp.get(i);
        }
        return users;
    }
    
    protected static boolean add(String name, String password) throws FileNotFoundException, IOException{
        //EFFECTS: Checks if name or password already exist, if not then appends user info to EOF
        String[][] users = getLog();
        
        //Check if name or password already exist
        for (String[] user : users) {
            if(user[0].toLowerCase().equals(name.toLowerCase()) || user[1].equals(password)){
                System.out.println("Name or password already exists");
                return false;
            }
        }
        //Append User to EOF
        try (FileWriter fileWrite = new FileWriter(filepath, true)) {
            fileWrite.append("\n" + name + "\t" + password + "\t" + 0);
            fileWrite.close();
        }catch (IOException e) {
            System.out.println("IO Error");
            return false;
        }
        
        return true;

    }
    
    protected static boolean remove(String name) throws FileNotFoundException, IOException{
        //EFFECTS: Checks if name exists, if so then rewrites the file without the user's info
        String[][] users = getLog();
        int row = -1;
        //Check if name exists
        for (int i=0; i<users.length;i++) {
            if(users[i][0].toLowerCase().equals(name.toLowerCase())){
                row = i;
                break;
            }
        }
        if(row==-1){
            System.out.println("Name does not exist");
            return false;
        }
        try (FileWriter fileWrite = new FileWriter(filepath)){
            fileWrite.write("Name:\tPassword:\tPoints:");
            for (int i=0; i<users.length;i++){
                if (i!=row){
                    fileWrite.append("\n" + users[i][0] + "\t" + users[i][1] + "\t" + users[i][2]);
                }
            }
            fileWrite.close();
        }
        
        
        return true;
    }
    
    protected static boolean isOwner(String name, String password){
        //EFFECTS: returns true if name and password belong to owner
        return name.equals(ownerName)&&password.equals(ownerPassword);
    }
    
    protected static String isCustomer(String name, String password) throws IOException{
        //EFFECTS: Checks if name & password match a user, if so returns users name
        String[][] users = getLog();
        
        //Checks if name & password match a user
        for (String[] user : users) {
            if(user[0].equals(name) && user[1].equals(password)){
                return user[0];
            }
        }
        System.out.println("Username or password are incorrect");
        return null;
    }
    
    
    
    /**
     * Temp Main
     * @param args
     * @throws java.io.IOException   */
    
    
    public static void main(String args[]) throws IOException{
        add("Jane", "AbCd");
        add("Karl", "1!j3");
        add("Mohammed", "sd90");
        
        String[][] users = getLog();
        for (String[] x : users){
            System.out.println(x[0] + ", " + x[1]);
        }
        System.out.println(isOwner("Julian", "badP4ssw0rd") + "\t" + isOwner("Julian", "badPassw0rd"));
        System.out.println(isCustomer("Karl", "1!j3"));
        
        remove("jane");
        
    }
    
}
