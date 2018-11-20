/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partslist;

/**
 *
 * @author Dan
 */
public class Material {

    String type;
    int qty;

    public Material(String type, int qty) {
        this.type = type;
        this.qty = qty;
    }

    public String getType() {
        return type;
    }

    public int getQty() {
        return qty;
    }

}
