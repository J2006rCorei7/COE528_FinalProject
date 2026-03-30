/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

public interface State {
    void buy(Customer customer, double cost);
    void redeemPointsAndBuy(Customer customer, double cost);
    String getStatus();
    double getDiscountedCost();
    void setDiscountedCost(double discountedCost);
}
