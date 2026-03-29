/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package frontEnd;
import javax.swing.*;
import java.awt.*;
import swingPackage.*;



/**
 * Date:        Mar 24, 2026
 * @author:     Julian Reyes    
 * Student #:   [STUDENT NUMBER REMOVED]
 * Course:      COE528 
 * Section:     05
 * TA:          [REMOVED]
 * 
 *  Lab # - (Description)
 *
 */

// Rather than createing a new frame instance, extend JFrame.
public class BookStoreApp extends JFrame{
    // Layout Manager
        // Note: CardLayout can only hold one card in the frame at a time.
    private CardLayout layout;
    
    // Main Container that holds all the screen panels. 
    private JPanel mainPanel;

    // Screen Instances (each window...)
    private LoginPanel loginPanel;
    private OwnerHomePanel ownerHomePanel;
    private OwnerBookPanel ownerBookPanel;
    private OwnerCustomerPanel ownerCustomerPanel;
    
    public BookStoreApp() {
        setTitle("Book Store");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Card System
        layout = new CardLayout();
        mainPanel = new JPanel(layout);
        
        // Panels 
        loginPanel = new LoginPanel(this);
        ownerHomePanel = new OwnerHomePanel(this);
        ownerBookPanel = new OwnerBookPanel(this);
        ownerCustomerPanel = new OwnerCustomerPanel(this);
        
        // Main panel
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(ownerHomePanel, "OWNERHOME");
        mainPanel.add(ownerBookPanel, "OWNERBOOK");
        mainPanel.add(ownerCustomerPanel, "OWNERCUSTOMER");
        
        
        // Places card container into the frame.
        add(mainPanel);
        
        layout.show(mainPanel, "LOGIN");
        
        
        
        setVisible(true);
        
    }
    
    
    
    // Navigation Methods
    public void showLogin() {
        layout.show(mainPanel, "LOGIN");
    }
    
    public void showOwnerHome(){
        layout.show(mainPanel, "OWNERHOME");
    }
    
    public void showOwnerBook(){
        layout.show(mainPanel, "OWNERBOOK");
    }
    public void showOwnerCustomer(){
        layout.show(mainPanel, "OWNERCUSTOMER");
    }
    
    
    
}
