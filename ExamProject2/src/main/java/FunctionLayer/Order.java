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
    ArrayList<Orderline> ol;

    public Order(int uID, double tPrice, Partslist pl) {
        this.uID = uID;
        this.tPrice = tPrice;
        this.pl = pl;
    }


    public Order(Date dDate, int oID, int ueID, double tPrice, ArrayList ol) {
        this.dDate = dDate;        
        this.oID = oID;
        this.ueID = ueID;
        this.tPrice = tPrice;
        this.ol = ol;
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

    
    
    
    
    
}


/*
  `oID` INT(11) NOT NULL AUTO_INCREMENT,
  `poID` INT(11) NOT NULL,
  `uID` INT(11) NOT NULL,
  `ueID` INT(11) NULL DEFAULT NULL,
  `tPrice` DOUBLE(11,2) NOT NULL,
  `DispatchDate` DATETIME NULL DEFAULT NULL,
*/