package FunctionLayer;

import DBAccess.UserMapper;
import VisualRender.BasicCarportDrawer;
import calculators.*;
import java.util.ArrayList;
import partslist.*;

/**
 * The purpose of LogicFacade is to...
 *
 * @author kasper
 */
public class LogicFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser(user);
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
     * @param length
     * @param width
     * @param slope
     * @return
     */
    public ArrayList<Double> getRoofInfo(double length, double width, int slope) {
        SpecialRoofCalculator src = new SpecialRoofCalculator(length, width, slope);
        ArrayList<Double> roofInfo = new ArrayList();
        roofInfo.add(src.getHeightOfRoof());
        roofInfo.add(src.getRafterLenght());
        roofInfo.add(src.getAreaOfRoof());
        roofInfo.add(src.getAreaOfGable());

        return roofInfo;
    }

    public Partslist createPartslist(double length, double width, boolean specialRoof, boolean hasShed,
            int numOfLogs, int numOfRafters, double lengthOfRafter, int numOfStrops, double areaOfRoof,
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
        } else {
            plc.addFlatRoof(areaOfRoof, pl);
            plc.addBand(lengthOfBand, pl);
            plc.addWaterBoard(length, width, pl);
        }
        if(hasShed){
            plc.addShed(numOfShedLogs, mOfWallPlank, mOfWallSupport, pl);
        }
        plc.addScrews(pl);
        return pl;
    }
    
    public String drawCarport(double length, double width, boolean hasShed){
        String carportString;
        BasicCarportDrawer bcd = new BasicCarportDrawer(length, width);
        bcd.setHasShed(hasShed);
        carportString = bcd.startDraw();
        return carportString;
    }

}
