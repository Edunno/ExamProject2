/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partslist;

import DBAccess.DataMapper;
import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public class PartsListCreator {

    DataMapper dm = new DataMapper();
    ArrayList<Wood> listOfWood = dm.createDummyWood();
    ArrayList<Material> listOfMats = dm.createDummyMats();
    
    final int LOGID = 109;    
    
    public Partslist createPartslist(){
        Partslist pl = new Partslist();
        addWoodToPartlist(LOGID, 4, pl);
        return pl;
    }
    

    public void addWoodToPartlist(int woodID, int qty, Partslist pl) {
        Wood wood = dm.getWood(listOfWood, woodID);
        wood.setQty(qty);
        pl.getWoodList().add(wood);
    }

    public void addMatToPartlist(int matID, int qty, Partslist pl) {
        Material mat = dm.getMaterial(listOfMats, matID);
        mat.setQty(qty);
        pl.getMatList().add(mat);
    }
    
 

}
