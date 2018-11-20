/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partslist;

import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public class Partslist {
    
    ArrayList<Wood> woodList;
    ArrayList<Material> matList;

    public Partslist() {
        woodList = new ArrayList();
        matList = new ArrayList();
    }

    public ArrayList<Wood> getWoodList() {
        return woodList;
    }

    public ArrayList<Material> getMatList() {
        return matList;
    }
    
    public void concat(Partslist pl){
        matList.addAll(pl.getMatList());
        woodList.addAll(pl.getWoodList());
    }


    
    
    
}