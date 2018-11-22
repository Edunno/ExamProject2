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

    public Partslist createPartslist(double length, double width) {
        Partslist pl = new Partslist();
        widthCM = (int) (width * 10);
        lengthCM = (int) (length * 10);
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

    public void addStropsToPartslist(int numOfStrops, Partslist pl) {
        int woodID;
        if (lengthCM > 400) {
            woodID = 108;
        } else {
            woodID = 107;
        }
        addWoodToPartslist(woodID, numOfStrops, "Remme i sider, sadles ned i stolper", pl);
    }

    public void addFlatRoofToPartslist(double areaOfRoof, Partslist pl) {
        int roofPlateArea = 6;
        int amountOfRoofPlates = (int) ((areaOfRoof / roofPlateArea) + 1);
        int woodID = 111;
        addWoodToPartslist(woodID, amountOfRoofPlates, "Tagplader, monteres på spær", pl);
    }

    public void addSpecialRooftoPartslist(double areaOfRoof, double areaOfGable, Partslist pl) {
        int amountOfRoofTiles = (int) (areaOfRoof / 0.1) + 10;
        int amountOfBoards = (int) (areaOfGable / 0.5) + 1;
        int matID = 213;
        int woodID = 110;
        addWoodToPartslist(woodID, amountOfBoards, "Beklædning til gavl", pl);
        addMatToPartslist(matID, amountOfRoofTiles, "Tagsten monteres på spær", pl);
    }

    public void addBandToPartslist(double lengthOfBand, Partslist pl) {
        int amount = (int) lengthOfBand / 20 + 1;
        int matID = 202;
        addMatToPartslist(matID, amount, "Til vindkryds på spær", pl);
    }

    public void addWoodToPartslist(int woodID, int qty, String desc, Partslist pl) {
        Wood wood = dm.getWood(listOfWood, woodID);
        Wood newWood = new Wood(wood.getId(), wood.getName(), wood.getPrice(), wood.getHeight(), wood.getWidth(), wood.getLength(), qty);
        newWood.setDescription(desc);
        pl.getWoodList().add(newWood);
    }

    public void addMatToPartslist(int matID, int qty, String desc, Partslist pl) {
        Material mat = dm.getMaterial(listOfMats, matID);
        mat.setQty(qty);
        mat.setDescription(desc);
        pl.getMatList().add(mat);
    }

}
