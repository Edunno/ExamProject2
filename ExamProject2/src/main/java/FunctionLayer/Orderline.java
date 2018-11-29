/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author KimPPedersen
 */
public class Orderline {
    
    int oID;
    int pID;
    int Qty;
    double lPrice;

    public Orderline(int oID, int pID, int Qty, double lPrice) {
        this.oID = oID;
        this.pID = pID;
        this.Qty = Qty;
        this.lPrice = lPrice;
    }

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int Qty) {
        this.Qty = Qty;
    }

    public double getlPrice() {
        return lPrice;
    }

    public void setlPrice(double lPrice) {
        this.lPrice = lPrice;
    }
    
    
    
            
    
    
    
    
    
}
