/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.partslist;

import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public class Partslist {
    
    ArrayList<Wood> woodList; //The list of wood
    ArrayList<Material> matList; // The list of materials

    public Partslist() {
        woodList = new ArrayList();
        matList = new ArrayList();
    }

    /**
     * Returns the list of wood from the partslist
     * 
     * @return 
     */
    public ArrayList<Wood> getWoodList() {
        return woodList;
    }
    
    /**
     * Returns the list of materials from the partslist
     * 
     * @return 
     */

    public ArrayList<Material> getMatList() {
        return matList;
    }
    
    /**
     * Adds two partslists together by adding the wood lists and material lists
     * 
     * @param pl the partslists to add 
     */
    
    public void concat(Partslist pl){
        matList.addAll(pl.getMatList());
        woodList.addAll(pl.getWoodList());
    }
    
    /**
     * Returns the total price of the wood and material list.
     * 
     * @return 
     */

    public int getTotalPrice(){
    int totalPrice = 0;
    for(Wood w : woodList){
        totalPrice += w.getPrice() * w.getQty();
    }
    for(Material m : matList){
        totalPrice += m.getPrice() * m.getQty();
    }
    return totalPrice;
    }
    
    
    
}