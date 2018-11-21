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
    
    int widthCM;
    int lengthCM;
    int heightCM;
            
    DataMapper dm = new DataMapper();
    ArrayList<Wood> listOfWood = dm.createDummyWood();
    ArrayList<Material> listOfMats = dm.createDummyMats();


    public Partslist createPartslist(int length, int width) {
        Partslist pl = new Partslist();
        widthCM = width;
        lengthCM = length;
        heightCM = 210;
        return pl;
    }

    public void addLogsToPartslist(int numOfLogs, Partslist pl) {
        int woodID = 109;
        addWoodToPartslist(woodID, numOfLogs, "Stolper nedgraves 90cm i jord", pl);
        
    }

    public void addRaftersToPartslist(double lengthOfRafter, int numOfRafters, Partslist pl) {
        int woodID;
        if (lengthOfRafter > 4) {
            woodID = 108;
        } else {
            woodID = 107;
        }
        addWoodToPartslist(woodID, numOfRafters, "Spær, monteres på rem", pl);
    }
    
    
    public void addStropsToPartslist(int numOfStrops, Partslist pl){
        int woodID;
        if(lengthCM > 400){
            woodID = 108;
        } else {
            woodID = 107;
        }
         addWoodToPartslist(woodID, numOfStrops, "Remme i sider, sadles ned i stolper", pl);
    }
    
    public void addRoofToPartslist(double areaOfRoof, double roofPlateArea, Partslist pl){
        int amountOfRoofPlates = (int) ((areaOfRoof/roofPlateArea) +1);
        int woodID = 111;
        addWoodToPartslist(woodID, amountOfRoofPlates, "Tagplader, monteres på spær", pl);
    }
    
    
    

    public void addWoodToPartslist(int woodID, int qty, String desc, Partslist pl) {
        Wood wood = dm.getWood(listOfWood, woodID);
        wood.setQty(qty);
        wood.setDescription(desc);
        pl.getWoodList().add(wood);
    }

    public void addMatToPartslist(int matID, int qty, String desc, Partslist pl) {
        Material mat = dm.getMaterial(listOfMats, matID);
        mat.setQty(qty);
        mat.setDescription(desc);
        pl.getMatList().add(mat);
    }

}
