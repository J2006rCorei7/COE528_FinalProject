/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

public class SilverState implements State {
    private static double discountedCost;
    
    @Override
    public void buy(Customer customer, double cost) {
        if (cost < 0) return;

        customer.addPoints((int)(10*cost));
        customer.updateState();
    }

    @Override
    public void redeemPointsAndBuy(Customer customer, double cost) {
        if (cost < 0) return;
        
        int remainderPoints = customer.getPoints() % 100;
        
        int redeemablePoints = Math.min(customer.getPoints(), (int)(cost * 100));
        double finalCost = cost - (redeemablePoints / 100.0);
        setDiscountedCost(redeemablePoints / 100);
        
        customer.removePoints(redeemablePoints);
        customer.addPoints((int)(10 * finalCost) + remainderPoints);
        customer.updateState();
    }

    @Override
    public String getStatus() {
        return "Silver";
    }
    
    @Override
    public double getDiscountedCost() {
        return discountedCost;
    }
    
    @Override
    public void setDiscountedCost(double discountedCost) {
        this.discountedCost = discountedCost;
    }
}
