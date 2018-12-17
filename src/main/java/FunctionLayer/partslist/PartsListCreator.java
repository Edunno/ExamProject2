/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.partslist;

import DataAccess.MaterialMapper;
import FunctionLayer.calculators.SpecialRoofRaftersCalculator;
import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public class PartsListCreator {

    int widthCM; // The width of the Carport in CM
    int lengthCM; // The length of the Carport in CM
    int heightCM;  // The height of the Carport in CM
    int screwTotal;  // The sum of various screws needed
    int shortBoardID = 103;
    int longBoardID = 104;
    int leagteID = 105;
    int reglarID = 106;
    int shortRafterID = 107;
    int longRafterID = 108;
    int logID = 109;
    int generalBoardID = 110;
    int plasticRoofID = 111;

    MaterialMapper dm = new MaterialMapper();
    ArrayList<Wood> listOfWood = dm.getAllWood();
    ArrayList<Material> listOfMats = dm.getAllMaterials();

    public Partslist createPartslist(double length, double width) {
        Partslist pl = new Partslist();
        widthCM = (int) (width * 10);
        lengthCM = (int) (length * 10);
        heightCM = 210;
        return pl;
    }

    /**
     * This method adds the logs and materials needed for them to the partslist
     *
     * @param numOfLogs is the number of logs required
     * @param pl the partslist being affected
     */
    public void addLogs(int numOfLogs, Partslist pl) {
        addWoodToPartslist(logID, numOfLogs, "Stolper nedgraves 90cm i jord", pl);
        addMatToPartslist(207, numOfLogs * 2, "Til montering af rem på stolper", pl);
        addMatToPartslist(208, numOfLogs * 2, "Til montering af rem på stolper", pl);
    }

    /**
     * This method adds vindskeder and screws to the partslist.
     *
     * These are the boards that protect the outer rafters and roof from the
     * elements
     *
     *
     * @param lengthOfRafter length of the rafter is needed to find out how long
     * the board should be
     * @param pl the partslist being affected
     */
    public void addVindskeder(double lengthOfRafter, Partslist pl) {
        int amountOfVindskeder = (int) (lengthOfRafter * 4) / 5 + 1;
        int screwsNeededPerBoard = 10;
        int partNumber;
        if (lengthOfRafter * 2 > 4) {
            partNumber = longBoardID;
        } else {
            partNumber = shortBoardID;
        }
        addToScrewCount(amountOfVindskeder * screwsNeededPerBoard);
        addWoodToPartslist(partNumber, amountOfVindskeder, "Vindskeder med rejsning", pl);
    }

    /**
     * This method adds waterboards to the partslist.
     *
     * These boards protect the rafters from the elements
     *
     *
     * @param length of the carport
     * @param width of the caport
     * @param pl the partslist being affected
     */
    public void addWaterBoard(double length, double width, Partslist pl) {
        int amountForLength = (int) (length / 5) + 1;
        int amountForWidth = (int) (width / 5) + 1;
        int screwsNeededPerBoard = 10;
        addToScrewCount((amountForLength * screwsNeededPerBoard) + (amountForLength * screwsNeededPerBoard));
        addWoodToPartslist(generalBoardID, amountForLength, "Vandbræt på stern i sider", pl);
        addWoodToPartslist(generalBoardID, amountForWidth, "Vandbræt på stern i ender", pl);
    }

    /**
     * ********************
     * This method adds rafters to the partslist.
     *
     *
     * @param lengthOfRafter is the length of the rafters
     * @param numOfRafters is the number of rafters
     * @param specialRoof
     * @param pl
     */
    public void addRafters(double lengthOfRafter, int numOfRafters, boolean specialRoof, Partslist pl) {
        int partNumber = 0;
        int screwsNeededPerBoard = 8;
        if (lengthOfRafter > 4) {
            partNumber = longRafterID;
        } else {
            partNumber = shortRafterID;
        }
        addToScrewCount(screwsNeededPerBoard * numOfRafters);
        addWoodToPartslist(partNumber, numOfRafters, "Spær, monteres på rem", pl);
    }

    /**
     * This method adds strops to the partslist.
     *
     * @param numOfStrops number of strops
     * @param pl the partslist being affected
     */
    public void addStrops(int numOfStrops, Partslist pl) {
        int partNumber;
        if (lengthCM > 400) {
            partNumber = longRafterID;
        } else {
            partNumber = shortRafterID;
        }
        addWoodToPartslist(partNumber, numOfStrops, "Remme i sider, sadles ned i stolper", pl);
    }

    /**
     * This method adds all wood and material needed for the shed on the
     * partslist.
     *
     *
     * @param numOfLogs number of logs needed for the shed
     * @param mOfWall is the meters of boards needed for the exterior
     * @param mOfWallSupport is the meters of boards needed for the support of
     * the exterior
     * @param pl is the partslist affected
     */
    public void addShed(int numOfLogs, double mOfWall, double mOfWallSupport, Partslist pl) {
        int numOfWallPlanks = (int) (mOfWall / 5) + 1;
        int numOfWallSupportPlanks = (int) (mOfWallSupport / 3) + 1;
        int screwsNeededPerBoard = 6;
        addWoodToPartslist(generalBoardID, numOfWallPlanks, "Beklædning af skur, skæres selv", pl);
        addWoodToPartslist(reglarID, numOfWallSupportPlanks, "Løsholter til skur", pl);
        addWoodToPartslist(logID, numOfLogs, "Stolper til skur", pl);
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

    /**
     * This method adds the wood and materials needed for a flat roof to the
     * partslist
     *
     * @param areaOfRoof area of the roof in meters
     * @param pl the partslist being affected
     */
    public void addFlatRoof(double areaOfRoof, Partslist pl) {
        int roofPlateArea = 6;
        int amountOfRoofPlates = (int) ((areaOfRoof / roofPlateArea) + 1);
        int matID = 201;
        int amountOfScrews = (amountOfRoofPlates * 50) / 200 + 1;
        addWoodToPartslist(plasticRoofID, amountOfRoofPlates, "Tagplader, monteres på spær", pl);
        addMatToPartslist(matID, amountOfScrews, "Skruer til tagplader", pl);
    }

    /**
     * This method adds the wood and materials needed for a sloped roof to the
     * partslist
     *
     *
     * @param areaOfRoof the total area of the roof in meters squared
     * @param areaOfGable total area of the gable in meters squared
     * @param pl the partslist being affected
     */
    public void addSpecialRoof(double areaOfRoof, double areaOfGable, Partslist pl) {
        int amountOfRoofTiles = (int) (areaOfRoof / 0.1) + 10;
        int amountOfBoards = (int) (areaOfGable / 0.5) + 1;
        int matID = 213;
        addWoodToPartslist(generalBoardID, amountOfBoards, "Beklædning til gavl", pl);
        addToScrewCount(amountOfBoards * 8);
        addMatToPartslist(matID, amountOfRoofTiles, "Tagsten monteres på spær", pl);
    }

    /**
     * This method adds the band needed for a flat roof.
     *
     * @param lengthOfBand lenght of the band in meters
     * @param pl the partslist being affected
     */
    public void addBand(double lengthOfBand, Partslist pl) {
        int amount = (int) lengthOfBand / 20 + 1;
        int matID = 202;
        addMatToPartslist(matID, amount, "Til vindkryds på spær", pl);
        addMatToPartslist(206, amount * 3, "Til montering af hulbånd", pl);
    }

    /**
     * This method adds brackets to the partslist
     *
     * @param numOfRafters number of rafters
     * @param pl the partslist being affected
     */
    public void addBrackets(int numOfRafters, Partslist pl) {
        addMatToPartslist(203, numOfRafters, "Til montering af spær på rem", pl);
        addMatToPartslist(204, numOfRafters, "Til montering af spær på rem", pl);
    }

    /**
     * This method adds the total screw amount needed to the partslist.
     *
     * @param pl the partslist being affected
     */
    public void addScrews(Partslist pl) {
        int matID = 209;
        int amountOfBoxes = (screwTotal / 200) + 1;
        addMatToPartslist(matID, amountOfBoxes, "Skruer til alt andet", pl);
    }
    
    public void addSpecialRoofRafters(int numOfSRafters, Partslist pl){
        int qty = numOfSRafters + 1;
        addToScrewCount(qty * 10);
        addWoodToPartslist(leagteID, qty, "Til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte", pl);
    }
    
    /**
     * This method adds a wood object to the Partslist
     *
     * @param partNumber is the identication number of the part
     * @param qty is the amount of wood needed
     * @param desc is the description of
     * @param pl the partslist being affected
     */

    public void addWoodToPartslist(int partNumber, int qty, String desc, Partslist pl) {
        Wood wood = dm.getWood(listOfWood, partNumber);
        Wood newWood = new Wood(wood.getId(), wood.getName(), wood.getPrice(), wood.getHeight(), wood.getWidth(), wood.getLength(), qty, wood.getPartNumber());
        newWood.setDescription(desc);
        pl.getWoodList().add(newWood);
    }

    /**
     * This method adds a material object to the Partslist
     *
     * @param partNumber is the identication number of the part
     * @param qty is the amount of wood needed
     * @param desc is the description of
     * @param pl the partslist being affected
     */
    public void addMatToPartslist(int partNumber, int qty, String desc, Partslist pl) {
        Material mat = dm.getMaterial(listOfMats, partNumber);
        Material newMat = new Material(mat.getId(), mat.getName(), mat.getPrice(), qty, mat.getPartNumber());
        newMat.setDescription(desc);
        pl.getMatList().add(newMat);
    }

    /**
     * Adds a number of screws needed for the partslist
     *
     * @param amount the amount to add to the screw total
     */
    public void addToScrewCount(int amount) {
        screwTotal += amount;
    }

}
