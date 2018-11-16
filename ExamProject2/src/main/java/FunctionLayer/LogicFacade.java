package FunctionLayer;

import DBAccess.UserMapper;
import calculators.*;
import java.util.ArrayList;

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
    
    public double calculateRoof(double length, double width){
        RoofCalculator rc = new RoofCalculator();
        return rc.RoofCalc(length, width);
    }
    
    public double calculateBands(double length, double width){
        BandCalculator bc = new BandCalculator();
        return bc.bandCalc(length, width);
    }
    
    /**
     * Returns an ArrayList with the info from the SpecialRoofCalculator.
     * 
     * The order in the ArrayList is:
     * Height of roof
     * Rafter Length
     * Area of entire roof (both sides)
     * Area of both gables
     * 
     * @param length
     * @param width
     * @param slope
     * @return 
     */

    public ArrayList<Double> getRoofInfo(double length, double width, int slope){
        SpecialRoofCalculator src = new SpecialRoofCalculator(length, width, slope);
        ArrayList<Double> roofInfo = new ArrayList();
        roofInfo.add(src.getHeightOfRoof());
        roofInfo.add(src.getRafterLenght());
        roofInfo.add(src.getAreaOfRoof());
        roofInfo.add(src.getAreaOfGable());
        
        return roofInfo;
    }

}
