/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import FunctionLayer.calculators.BandCalculator;
import FunctionLayer.calculators.LogCalculator;
import FunctionLayer.calculators.RafterCalculator;
import FunctionLayer.calculators.RoofCalculator;
import FunctionLayer.calculators.SpecialRoofCalculator;
import FunctionLayer.calculators.StropCalculator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Esben
 */
public class CalcJUnitTest {

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public int testLogs(double aLen, double bLen) {
        LogCalculator lc = new LogCalculator();

        int r = lc.mainCalc(aLen, bLen);
        return r;
    }

    public double testRoof(double aLen, double bLen) {
        RoofCalculator rc = new RoofCalculator();

        double r = rc.RoofCalc(aLen, bLen);

        return r;
    }

    public double testBand(double aLen, double bLen) {
        BandCalculator bc = new BandCalculator();

        double r = bc.bandCalc(aLen, bLen);

        return r;
    }

    public int testRaft(double aLen, double bLen) {
        RafterCalculator rac = new RafterCalculator();

        int r1 = rac.RaftCalc(aLen, bLen);
//        int r2 = rac.SpecialRaftCalc(aLen, bLen);

        return r1;
//        assertEquals(18,r2);
    }

    public int testSpecRaft(double aLen, double bLen) {
        RafterCalculator rac = new RafterCalculator();
        int r = rac.SpecialRaftCalc(aLen, bLen);

        return r;
//        assertEquals(18,r2);
    }

    public SpecialRoofCalculator src(double aLen, double bLen, double slope) {
        SpecialRoofCalculator sr1 = new SpecialRoofCalculator(aLen, bLen, slope);
        return sr1;
    }

    @Test
    public void test1() {
        double aLen = 7.8;
        double bLen = 3.7;
        double slope = 32;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        int specialRoofRafts = testSpecRaft(aLen, bLen);

        SpecialRoofCalculator src1 = src(aLen, bLen, slope);
        double gable = src1.getAreaOfGable();
        double specialRoofArea = src1.getAreaOfRoof();
        double heightOfRoof = src1.getHeightOfRoof();
        double midRoofDegrees = src1.getMidRoofDegrees();
        double raftLength = src1.getRafterLenght();
        double roofLength = src1.getLengthOfRoof();
        double roofWidth = src1.getWidthOfRoof();

        assertEquals(28.86, roofArea, 0.0);
        assertEquals(15, rafters);
        assertEquals(17.26, bandLength, 0.1);
        assertEquals(8, logs);

        assertEquals(18, specialRoofRafts);
        assertEquals(4.27, gable, 0.1);
        assertEquals(34.0, specialRoofArea, 0.1);
        assertEquals(1.15, heightOfRoof, 0.1);
        assertEquals(116.0, midRoofDegrees, 0.1);
        assertEquals(2.18, raftLength, 0.1);
        assertEquals(7.8, roofLength, 0.1);
        assertEquals(3.7, roofWidth, 0.1);
    }

    @Test
    public void test2() {
        double aLen = 5.2;
        double bLen = 4.1;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(21.32, roofArea, 0.0);
        assertEquals(10, rafters);
        assertEquals(13.24, bandLength, 0.1);
        assertEquals(6, logs);
    }

    @Test
    public void test3() {
        double aLen = 3.2;
        double bLen = 5.11;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(16.352, roofArea, 0.0);
        assertEquals(10, rafters);
        assertEquals(12.05, bandLength, 0.1);
        assertEquals(6, logs);
    }

    @Test
    public void test4() {
        double aLen = 6.2;
        double bLen = 2.4;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(14.879999999999999, roofArea, 0.0);
        assertEquals(12, rafters);
        assertEquals(13.29, bandLength, 0.1);
        assertEquals(6, logs);
    }

    @Test
    public void test5() {
        double aLen = 8.2;
        double bLen = 4.11;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(33.702, roofArea, 0.0);
        assertEquals(15, rafters);
        assertEquals(18.34, bandLength, 0.1);
        assertEquals(8, logs);
    }

    @Test
    public void test6() {
        double aLen = 11.3;
        double bLen = 7.9;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(89.27000000000001, roofArea, 0.0);
        assertEquals(21, rafters);
        assertEquals(27.57, bandLength, 0.1);
        assertEquals(20, logs);
    }

    @Test
    public void test7() {
        double aLen = 10.0;
        double bLen = 3.3;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(33, roofArea, 0.0);
        assertEquals(19, rafters);
        assertEquals(21.06, bandLength, 0.1);
        assertEquals(10, logs);
    }

    @Test
    public void test8() {
        double aLen = 2.2;
        double bLen = 8.9;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(19.580000000000002, roofArea, 0.0);
        assertEquals(17, rafters);
        assertEquals(18.33, bandLength, 0.1);
        assertEquals(8, logs);
    }

    @Test
    public void test9() {
        double aLen = 5.5;
        double bLen = 5.5;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(30.25, roofArea, 0.0);
        assertEquals(11, rafters);
        assertEquals(15.55, bandLength, 0.1);
        assertEquals(9, logs);
    }

    @Test
    public void test10() {
        double aLen = 22.7;
        double bLen = 13.5;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(306.45, roofArea, 0.0);
        assertEquals(42, rafters);
        assertEquals(52.82, bandLength, 0.1);
        assertEquals(60, logs);
    }

    @Test
    public void test11() {
        double aLen = 7.3;
        double bLen = 3.6;
        double slope = 20;
        double roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        int specialRoofRafts = testSpecRaft(aLen, bLen);

        SpecialRoofCalculator src1 = src(aLen, bLen, slope);
        double gable = src1.getAreaOfGable();
        double specialRoofArea = src1.getAreaOfRoof();
        double heightOfRoof = src1.getHeightOfRoof();
        double midRoofDegrees = src1.getMidRoofDegrees();
        double raftLength = src1.getRafterLenght();
        double roofLength = src1.getLengthOfRoof();
        double roofWidth = src1.getWidthOfRoof();

        assertEquals(26.28, roofArea, 0.0);
        assertEquals(14, rafters);
        assertEquals(16.27, bandLength, 0.1);
        assertEquals(6, logs);

        assertEquals(18, specialRoofRafts);
        assertEquals(2.35, gable, 0.1);
        assertEquals(27.96, specialRoofArea, 0.1);
        assertEquals(0.65, heightOfRoof, 0.1);
        assertEquals(140.0, midRoofDegrees, 0.1);
        assertEquals(1.91, raftLength, 0.1);
        assertEquals(7.3, roofLength, 0.1);
        assertEquals(3.6, roofWidth, 0.1);
    }

    @Test
    public void testStrop1() {
        double a = 6.0;
        double b = 7.8;
        StropCalculator sc = new StropCalculator(a, b);
        assertEquals(7.8, sc.length(), 0.01);
    }

    @Test
    public void testStrop2() {
        double a = 12.0;
        double b = 9.0;
        StropCalculator sc = new StropCalculator(a, b);
        assertEquals(12, sc.length(), 0.01);
    }
}
