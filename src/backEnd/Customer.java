/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

/**
 *  This is a Customer object that stores info about a customer such as username, password, points and status. 
 *
 */
public class Customer extends State {
    // Instance variables
    private final String username;
    private final String password;
    
    // Initialize default values
    private int points = 0;
    private String status = "Silver";
    
    // Constructor only requires username and password because all customers start out with
    // the same status and amount of points.
    public Customer(String username, String password) { 
        this.username = username;
        this.password = password;
    }
    
    // EFFECTS: Adds points to this customer
    // ACCESS: Only buy() and redeemPointsAndBuy() are allowed to manipulate customer points
    private void addPoints(int points) {
        this.points += points;                      // Add points to total points
        if (this.points >= 1000) {status = "Gold";} // If total points now exceeds 1000, upgrade to Gold status
    }
    
    // EFFECTS: Removes points from this customer
    // ACCESS: Only buy() and redeemPointsAndBuy() are allowed to manipulate customer points
    private void removePoints(int points) {
        this.points -= points;                       // Remove points to total points
        if (this.points < 1000) {status = "Silver";} // If total points now do not exceed 1000, downgrade to Silver status
    }
    
    // EFFECTS: Awards this customer points for purchasing a book
    public void buy(double cost) {
        if (cost < 0) {                              // If cost is less than $0 CAD, cancel and throw error
            System.out.println("Cost less than $0 CAD");
            return;
        }
        addPoints((int)(10*cost)); // Buying a book adds 10 point for every $1 CAD spend
    }
    
    // EFFECTS: Redeems this customer's points to get a discounted price for a book
    public void redeemPointsAndBuy(double cost) {
        if (cost < 0) {                              // If cost is less than $0 CAD, cancel and throw error
            System.out.println("Cost less than $0 CAD");
            return;
        }
        
        // If the cost converted to points is higher than the total points, redeem all points, otherwise redeem the cost conversion
        int redeemablePoints = Math.min(this.points, (int)(cost * 100)); 
        
        // Subtract the redeemed points converted to CAD from final cost
        double finalCost = cost - (redeemablePoints / 100.0);
        
        // Remove redeemed points
        removePoints(redeemablePoints);
        // Buy now with discounted cost
        buy(finalCost);
    }
    
    public String getName() {
        return username;
    }
    
    // ACCESS: UserManager
    protected String getPassword() {
        return password;
    }
    
    public int getPoints() {
        return points;
    }
    
    public String getStatus() {
        return status;
    }
}
