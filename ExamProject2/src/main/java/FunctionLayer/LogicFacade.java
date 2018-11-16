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

    public int calculateLogs(int length, int width) {
        LogCalculator lc = new LogCalculator();
        return lc.mainCalc(length, width);
    }

    public int calculateRafters(int length, int width, boolean specialRoof) {
        RafterCalculator rc = new RafterCalculator();
        if (specialRoof) {
            return rc.SpecialRaftCalc(length, width);
        }
        return rc.RaftCalc(length, width);
    }
    
    public double calculateRoof(int length, int width){
        RoofCalculator rc = new RoofCalculator();
        return rc.RoofCalc(length, width);
    }
    
    public double calculateBands(int length, int width){
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

    public ArrayList<Double> getRoofInfo(int length, int width, int slope){
        SpecialRoofCalculator src = new SpecialRoofCalculator(length, width, slope);
        ArrayList<Double> roofInfo = new ArrayList();
        roofInfo.add(src.getHeightOfRoof());
        roofInfo.add(src.getRafterLenght());
        roofInfo.add(src.getAreaOfRoof());
        roofInfo.add(src.getAreaOfGable());
        
        return roofInfo;
    }

}
