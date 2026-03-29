/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

/**
 *  This is a Book object that stores info about a book such as name and price. 
 * 
 */
public class Book {
    private String name;
    private double price;
    
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    protected void setName(String name) {
        this.name = name;
    }
    
    protected void setPrice(double price) {
        this.price = price;
    }
}
