/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.sql.Date;
import java.util.ArrayList;
import partslist.Partslist;

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

    public Order(int uID, double tPrice, Partslist pl) {
        this.uID = uID;
        this.tPrice = tPrice;
        this.pl = pl;
    }

    public Order(Date dDate, int oID, int ueID, double tPrice, ArrayList aol) {
        this.dDate = dDate;
        this.oID = oID;
        this.ueID = ueID;
        this.tPrice = tPrice;
        this.aol = aol;
    }

    public Order(Date dDate, int oID, int ueID, double tPrice) {
        this.dDate = dDate;
        this.oID = oID;
        this.ueID = ueID;
        this.tPrice = tPrice;
    }

    public Order(Date dDate, int uID, int oID, int ueID, Double tPrice) {
        this.dDate = dDate;
        this.oID = oID;
        this.uID = uID;
        this.ueID = ueID;
        this.tPrice = tPrice;
    }

    public Order(int uID, int oID, int ueID, Double tPrice) {
        this.oID = oID;
        this.uID = uID;
        this.ueID = ueID;
        this.tPrice = tPrice;
    }

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getUeID() {
        return ueID;
    }

    public void setUeID(int ueID) {
        this.ueID = ueID;
    }

    public double gettPrice() {
        return tPrice;
    }

    public void settPrice(double tPrice) {
        this.tPrice = tPrice;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDATe) {
        this.dDate = dDATe;
    }

    public Partslist getPl() {
        return pl;
    }
    
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


    
    

}
