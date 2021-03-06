package FunctionLayer;

import FunctionLayer.DTO.User;
import FunctionLayer.DTO.Order;
import FunctionLayer.FogExceptions.FogCreateUserException;
import FunctionLayer.partslist.Partslist;
import FunctionLayer.partslist.PartsListCreator;
import FunctionLayer.calculators.SpecialRoofCalculator;
import FunctionLayer.calculators.StropCalculator;
import FunctionLayer.calculators.RafterCalculator;
import FunctionLayer.calculators.ShedCalculator;
import FunctionLayer.calculators.LogCalculator;
import FunctionLayer.calculators.BandCalculator;
import FunctionLayer.calculators.RoofCalculator;
import DataAccess.MaterialMapper;
import FunctionLayer.FogExceptions.FogLoginException;
import DataAccess.OrderMapper;
import DataAccess.UserMapper;
import FunctionLayer.FogExceptions.FogDataException;
import FunctionLayer.calculators.SpecialRoofRaftersCalculator;
import FunctionLayer.partslist.*;
import PresentationLayer.VisualRender.BasicCarportDrawer;
import PresentationLayer.VisualRender.FrontDrawer;
import PresentationLayer.VisualRender.SideDrawer;
import PresentationLayer.VisualRender.SpecialCarportDrawer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of LogicFacade is to connect the PresentationLayer with the DataAccess layer
 *
 * @author kasper
 */
public class LogicFacade {
    
    public static User login(String email, String password) throws FogLoginException {
        return UserMapper.login(email, password);
    }
    
    public static User createUser(String email, String password) throws FogCreateUserException {
        User user = new User(email, password, "customer");
        try {
            UserMapper.createUser(user);
        } catch (FogCreateUserException | ClassNotFoundException ex) {
            Logger.getLogger(LogicFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public int calculateLogs(double length, double width) {
        LogCalculator lc = new LogCalculator();
        return lc.mainCalc(length, width);
    }
    
    public int calculateRafters(double length, double width, boolean specialRoof) {
        RafterCalculator rc = new RafterCalculator();
        if (specialRoof) {
            return rc.SpecialRaftCalc(length, width);
        }
        return rc.RaftCalc(length, width);
    }
    public int calculateSRafters(double length, double width, int slope){
    SpecialRoofRaftersCalculator srrc = new SpecialRoofRaftersCalculator(length, width, slope);
    return srrc.roofRaftCalc();
    }
    
    public int calculateStrops(double length, double width) {
        StropCalculator sc = new StropCalculator(length, width);
        return sc.amount();
    }
    
    public double calculateRoof(double length, double width) {
        RoofCalculator rc = new RoofCalculator();
        return rc.RoofCalc(length, width);
    }
    
    public double calculateBands(double length, double width) {
        BandCalculator bc = new BandCalculator();
        return bc.bandCalc(length, width);
    }
    
    public ArrayList<Double> getShedInfo(double length, double width) {
        ShedCalculator sc = new ShedCalculator(length, width);
        ArrayList<Double> shedInfo = new ArrayList();
        shedInfo.add((double) sc.getLogs());
        shedInfo.add(sc.getmOfWall());
        shedInfo.add(sc.getmOfWallSupport());
        
        return shedInfo;
    }

    /**
     * Returns an ArrayList with the info from the SpecialRoofCalculator.
     *
     * The order in the ArrayList is: Height of roof Rafter Length Area of
     * entire roof (both sides) Area of both gables
     *
     * @param length of the carport
     * @param width of the carport
     * @param slope degrees of slope, if the roof is slanted
     * @return An ArrayList of double, containing info on the roof
     */
    public ArrayList<Double> getRoofInfo(double length, double width, int slope) {
        double numOfSRafters = calculateSRafters(length, width, slope);
        SpecialRoofCalculator src = new SpecialRoofCalculator(length, width, slope);
        ArrayList<Double> roofInfo = new ArrayList();
        roofInfo.add(numOfSRafters);
        roofInfo.add(src.getRafterLenght());
        roofInfo.add(src.getAreaOfRoof());
        roofInfo.add(src.getAreaOfGable());
        
        return roofInfo;
    }
    
    public Partslist createPartslist(double length, double width, boolean specialRoof, boolean hasShed,
            int numOfLogs, int numOfRafters, int numOfSRafters, double lengthOfRafter, int numOfStrops, double areaOfRoof,
            double lengthOfBand, double areaOfGable, int numOfShedLogs, double mOfWallPlank, double mOfWallSupport) {
        PartsListCreator plc = new PartsListCreator();
        Partslist pl = plc.createPartslist(length * 10, width * 10);
        plc.addLogs(numOfLogs, pl);
        plc.addRafters(lengthOfRafter, numOfRafters, specialRoof, pl);
        plc.addStrops(numOfStrops, pl);
        plc.addBrackets(numOfRafters, pl);
        if (specialRoof) {
            plc.addSpecialRoof(areaOfRoof, areaOfGable, pl);
            plc.addVindskeder(lengthOfRafter, pl);
            plc.addSpecialRoofRafters(numOfSRafters, pl);
        } else {
            plc.addFlatRoof(areaOfRoof, pl);
            plc.addBand(lengthOfBand, pl);
            plc.addWaterBoard(length, width, pl);
        }
        if (hasShed) {
            plc.addShed(numOfShedLogs, mOfWallPlank, mOfWallSupport, pl);
        }
        plc.addScrews(pl);
        return pl;
    }
    
    public String drawBasicCarport(double length, double width, boolean hasShed) {
        String carportString;
        BasicCarportDrawer bcd = new BasicCarportDrawer(length, width);
        bcd.setHasShed(hasShed);
        carportString = bcd.startDraw();
        return carportString;
    }
    
    public String drawSpecialCarport(double length, double width, double slope, boolean hasShed) {
        String carportString;
        SpecialCarportDrawer scd = new SpecialCarportDrawer(length, width, slope);
        scd.setHasShed(hasShed);
        carportString = scd.startDraw();
        return carportString;
    }
    
    public String drawSideCarport(double length, double width, int slope, boolean isSpecial, boolean hasShed) {
        String carportString;
        SideDrawer sd = new SideDrawer(length, width, isSpecial, hasShed);
        sd.setSpecialMeasures(slope);
        carportString = sd.startDraw();
        return carportString;
    }
    
    public String drawFrontCarport(double length, double width, int slope, boolean isSpecial, boolean hasShed) {
        String carportString;
        FrontDrawer fd = new FrontDrawer(length, width, isSpecial, hasShed);
        fd.setSpecialMeasures(slope);
        carportString = fd.startDraw();
        return carportString;
    }
    
    public static int storeOrder(Order o, double length, double width, boolean hasShed, int slope) throws FogDataException {
        int oID = 0;
        oID = OrderMapper.createOrder(o, length, width, hasShed, slope);
        return oID;
        
    }
    
    public static Order getOrderByOID(int oID) throws FogDataException {
        return OrderMapper.getOrderbyoID(oID);
    }
    
    public static ArrayList<Order> getOrdersByUID(int uID) throws FogDataException {
        return OrderMapper.getOrdersbyUID(uID);
    }
    
    public static ArrayList<Order> getOrdersNotDispatched() throws FogDataException {
        return OrderMapper.allOrdersNotDispatched();
    }
    
    public static ArrayList<Order> getAllOrders () throws FogDataException{
        return OrderMapper.getAllOrders();
    }
    
    public static void markAsDispatch(int oID) throws FogDataException {
        OrderMapper.markAsDispatch(oID);
    }
    
    public static void addMaterialToDB(Material m) throws FogDataException {
        MaterialMapper.addMatToDB(m);
    }
    
    public static void addWoodToDB(Wood w) throws FogDataException {
        MaterialMapper.addWoodToDB(w);
    }
    
    public static String getProductName(int pID) throws FogDataException {
        return MaterialMapper.getProductName(pID);
    }
    
    public static Carport getCarport(int oID) throws FogDataException {
        return OrderMapper.getCarport(oID);
    }
    
    public static int updateOrder(Order o, double length, double width, boolean hasShed, int slope) throws FogDataException {
        return OrderMapper.updateOrder(o, length, width, hasShed, slope);
    }
    
    public static ArrayList<Wood> getAllWood() {
        return MaterialMapper.getAllWood();
    }
    
    public static ArrayList<Material> getAllMaterials() {
        return MaterialMapper.getAllMaterials();
    }
    
    public static void removeMaterialFromDB(int pID) throws FogDataException {
        MaterialMapper.removeMaterialFromDB(pID);
    }
    
    public static void addStockToDB(int pID, int qty) throws FogDataException {
        MaterialMapper.addStock(pID, qty);
    }
    
    public static void changePartNumber(int pID, int partNumber) throws FogDataException{
        MaterialMapper.changePartNumber(pID, partNumber);
    }
    
    
}
