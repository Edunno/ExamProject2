/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.DTO;

import FunctionLayer.partslist.Carport;
import java.sql.Date;
import java.util.ArrayList;
import FunctionLayer.partslist.Partslist;

/**
 *
 * @author KimPPedersen
 */
public class Order {

    private int oID;
    private int uID;
    private int ueID;
    private double tPrice;
    private Date dDate;
    Partslist pl;
    ArrayList<Orderline> aol;
    public boolean toString;
    Carport cp;

    
    public Order(Date dDate, int oID, int uID, int ueID, double tPrice, ArrayList aol) {
        this.dDate = dDate;
        this.oID = oID;
        this.uID = uID;
        this.ueID = ueID;
        this.tPrice = tPrice;
        this.aol = aol;
    }
    
    /**
     * Returns the Order ID
     * 
     * @return order ID
     */

    public int getoID() {
        return oID;
    }
    
    /**
     * Sets the Order ID
     * 
     * @param oID the order ID
     */

    public void setoID(int oID) {
        this.oID = oID;
    }
    
    /**
     * Returns the user ID attached to the Order
     * 
     * @return user ID
     */

    public int getuID() {
        return uID;
    }
    
    /**
     * Sets the user ID on the Order
     * 
     * @param uID the user id
     */

    public void setuID(int uID) {
        this.uID = uID;
    }
    
    /**
     * This method returns the Employee id from the Order
     * 
     * 
     * @return employee id
     */

    public int getUeID() {
        return ueID;
    }
    
    /**
     * Sets the Employee ID to the Order
     * 
     * @param ueID the Employee ID
     */

    public void setUeID(int ueID) {
        this.ueID = ueID;
    }
    
    /**
     * This method returns the total price of the Order
     * 
     * @return  double total price
     */

    public double gettPrice() {
        return tPrice;
    }
    
    /**
     * This method sets the total price of the Order
     * 
     * @param tPrice the total price
     */

    public void settPrice(double tPrice) {
        this.tPrice = tPrice;
    }
    
    /**
     * This method returns the dispatch date of the Order
     * 
     * @return 
     */

    public Date getdDate() {
        return dDate;
    }
    
    /**
     *This method returns the Partslist object of the Order 
     * 
     * @return the Partslist
     */
    
    public Partslist getPl() {
        return pl;
    }
    
    /**
     * This method returns an the Orderlines from the Order
     * 
     * @return an ArrayList<Orderline> from the Order
     */
    
        public ArrayList<Orderline> getAol() {
        return aol;
    }

    public void setAol(ArrayList<Orderline> aol) {
        this.aol = aol;
    }

    public String allOrdersByIDToString() {
        return "Order{" + "oID=" + oID + ", uID=" + uID + ", ueID=" + ueID + ", tPrice=" + tPrice + ", dDate=" + dDate + '}';
    }

    @Override
    public String toString() {
        return "Order{" + "oID=" + oID + ", uID=" + uID + ", ueID=" + ueID + ", tPrice=" + tPrice + '}';
    }

    public void setPl(Partslist pl) {
        this.pl = pl;
    }

    public Carport getCp() {
        return cp;
    }

    public void setCp(Carport cp) {
        this.cp = cp;
    }

    

    
    

}
