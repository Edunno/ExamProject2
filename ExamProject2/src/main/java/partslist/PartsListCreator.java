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
    int screwTotal;

    DataMapper dm = new DataMapper();
    ArrayList<Wood> listOfWood = dm.getAllWood();
    ArrayList<Material> listOfMats = dm.getAllMaterials();

    public Partslist createPartslist(double length, double width) {
        Partslist pl = new Partslist();
        widthCM = (int) (width * 10);
        lengthCM = (int) (length * 10);
        heightCM = 210;
        return pl;
    }

    public void addLogs(int numOfLogs, Partslist pl) {
        int woodID = 109;
        addWoodToPartslist(woodID, numOfLogs, "Stolper nedgraves 90cm i jord", pl);
        addMatToPartslist(207, numOfLogs * 2, "Til montering af rem på stolper", pl);
        addMatToPartslist(208, numOfLogs * 2, "Til montering af rem på stolper", pl);
    }

    public void addVindskeder(double lengthOfRafter, Partslist pl) {
        int amountOfVindskeder = (int) (lengthOfRafter * 4) / 5 + 1;
        int screwsNeededPerBoard = 10;
        int woodID;
        if (lengthOfRafter * 2 > 4) {
            woodID = 104;
        } else {
            woodID = 103;
        }
        addToScrewCount(amountOfVindskeder * screwsNeededPerBoard);
        addWoodToPartslist(woodID, amountOfVindskeder, "Vindskeder med rejsning", pl);
    }

    public void addWaterBoard(double length, double width, Partslist pl) {
        int amountForLength = (int) (length / 5) + 1;
        int amountForWidth = (int) (width / 5) + 1;
        int woodID = 110;
        int screwsNeededPerBoard = 10;
        addToScrewCount((amountForLength * screwsNeededPerBoard) + (amountForLength * screwsNeededPerBoard));
        addWoodToPartslist(woodID, amountForLength, "Vandbræt på stern i sider", pl);
        addWoodToPartslist(woodID, amountForWidth, "Vandbræt på stern i ender", pl);
    }

    public void addRafters(double lengthOfRafter, int numOfRafters, boolean specialRoof, Partslist pl) {
        int woodID = 0;
        int screwsNeededPerBoard = 8;
        if (specialRoof) {
            if ((lengthOfRafter * 2) > 4) {
                woodID = 108;
            } else {
                woodID = 107;
            }
        }

        if (!specialRoof) {
            if (lengthOfRafter > 4) {
                woodID = 108;
            } else {
                woodID = 107;
            }
        }
        addToScrewCount(screwsNeededPerBoard * numOfRafters);
        addWoodToPartslist(woodID, numOfRafters, "Spær, monteres på rem", pl);
    }

    public void addStrops(int numOfStrops, Partslist pl) {
        int woodID;
        if (lengthCM > 400) {
            woodID = 108;
        } else {
            woodID = 107;
        }
        addWoodToPartslist(woodID, numOfStrops, "Remme i sider, sadles ned i stolper", pl);
    }

    public void addShed(int numOfLogs, double mOfWall, double mOfWallSupport, Partslist pl) {
        int numOfWallPlanks = (int) (mOfWall / 5) + 1;
        int numOfWallSupportPlanks = (int) (mOfWallSupport / 3) + 1;
        int screwsNeededPerBoard = 6;
        addWoodToPartslist(110, numOfWallPlanks, "Beklædning af	skur, skæres selv", pl);
        addWoodToPartslist(106, numOfWallSupportPlanks, "Løsholter til skur", pl);
        addWoodToPartslist(109, numOfLogs, "Stolper til skur", pl);
        addMatToPartslist(211, 1, "Til dør i skur", pl);
        addMatToPartslist(212, 2, "Til dør i skur", pl);
        addToScrewCount(screwsNeededPerBoard * numOfWallSupportPlanks);
        addToScrewCount(screwsNeededPerBoard * numOfWallPlanks);
        for (Material m : pl.getMatList()) {
            if (m.getId() == 207) {
                m.setQty(m.getQty() + (numOfLogs * 2));
            }
            if (m.getId() == 208) {
                m.setQty(m.getQty() + (numOfLogs * 2));
            }
        }

    }

    public void addFlatRoof(double areaOfRoof, Partslist pl) {
        int roofPlateArea = 6;
        int amountOfRoofPlates = (int) ((areaOfRoof / roofPlateArea) + 1);
        int woodID = 111;
        int matID = 201;
        int amountOfScrews = (amountOfRoofPlates * 50) / 200;
        addWoodToPartslist(woodID, amountOfRoofPlates, "Tagplader, monteres på spær", pl);
        addMatToPartslist(matID, amountOfScrews, "Skruer til tagplader", pl);
    }

    public void addSpecialRoof(double areaOfRoof, double areaOfGable, Partslist pl) {
        int amountOfRoofTiles = (int) (areaOfRoof / 0.1) + 10;
        int amountOfBoards = (int) (areaOfGable / 0.5) + 1;
        int matID = 213;
        int woodID = 110;
        addWoodToPartslist(woodID, amountOfBoards, "Beklædning til gavl", pl);
        addMatToPartslist(matID, amountOfRoofTiles, "Tagsten monteres på spær", pl);
    }

    public void addBand(double lengthOfBand, Partslist pl) {
        int amount = (int) lengthOfBand / 20 + 1;
        int matID = 202;
        addMatToPartslist(matID, amount, "Til vindkryds på spær", pl);
        addMatToPartslist(206, amount * 3, "Til montering af hulbånd", pl);
    }

    public void addBrackets(int numOfRafters, Partslist pl) {
        addMatToPartslist(203, numOfRafters, "Til montering af spær på rem", pl);
        addMatToPartslist(204, numOfRafters, "Til montering af spær på rem", pl);
    }

    public void addScrews(Partslist pl) {
        int matID = 209;
        int amountOfBoxes = (screwTotal / 200) + 1;
        addMatToPartslist(matID, amountOfBoxes, "Skruer til alt andet", pl);
    }

    public void addWoodToPartslist(int woodID, int qty, String desc, Partslist pl) {
        Wood wood = dm.getWood(listOfWood, woodID);
        Wood newWood = new Wood(wood.getId(), wood.getName(), wood.getPrice(), wood.getHeight(), wood.getWidth(), wood.getLength(), qty);
        newWood.setDescription(desc);
        pl.getWoodList().add(newWood);
    }

    public void addMatToPartslist(int matID, int qty, String desc, Partslist pl) {
        Material mat = dm.getMaterial(listOfMats, matID);
        Material newMat = new Material(mat.getId(), mat.getName(), mat.getPrice(), qty);
        newMat.setDescription(desc);
        pl.getMatList().add(newMat);
    }

    public void addToScrewCount(int amount) {
        screwTotal += amount;
    }

}
