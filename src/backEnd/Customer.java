/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

/**
 *  This is a Customer object that stores info about a customer such as username, password, points and status. 
 *
 */
public class Customer {
    // Instance variables
    private final String username;
    private final String password;
    
    // Initialize default values
    private int points;
    private State state;
    
    // Constructor only requires username and password because all customers start out with
    // the same status and amount of points.
    public Customer(String username, String password) { 
        this(username, password, 0); // Calls secondary constructor and initializes points to zero
    }
    
    public Customer(String username, String password, int points) { 
        this.username = username;
        this.password = password;
        this.points = points;
        updateState();
    }
    
    final void updateState() {
        if (points >= 1000) {
            state = new GoldState();
        }
        else {
            state = new SilverState();
        }
    }
    
    // EFFECTS: Adds points to this customer
    // ACCESS: Only buy() and redeemPointsAndBuy() are allowed to manipulate customer points
    protected void addPoints(int points) {
        this.points += points;                      // Add points to total points
    }
    
    // EFFECTS: Removes points from this customer
    // ACCESS: Only buy() and redeemPointsAndBuy() are allowed to manipulate customer points
    protected void removePoints(int points) {
        this.points -= points;                       // Remove points to total points
        if (this.points < 0) this.points = 0;
    }
    
    // EFFECTS: Awards this customer points for purchasing a book
    public void buy(double cost) {
        state.buy(this, cost);
        updateState();
        UserManager.saveCurrentCustomer();
    }
    
    // EFFECTS: Redeems this customer's points to get a discounted price for a book
    public void redeemPointsAndBuy(double cost) {
        state.redeemPointsAndBuy(this, cost);
        updateState();
        UserManager.saveCurrentCustomer();
    }
    
    public String getName() {
        return username;
    }
   
    public String getPassword() {
        return password;
    }
    
    public int getPoints() {
        return points;
    }
    
    public String getStatus() {
        return state.getStatus();
    }
    
    public double getDiscountedCost() {
        System.out.println("Customer getDiscountedCost() = " + state.getDiscountedCost());
        return state.getDiscountedCost();
    }
    
    protected void setDiscountedCost(double discountedCost) {
        state.setDiscountedCost(discountedCost);
    }
}
