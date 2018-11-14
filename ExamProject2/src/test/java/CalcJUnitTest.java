/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import calculators.BandCalculator;
import calculators.LogCalculator;
import calculators.RafterCalculator;
import calculators.RoofCalculator;
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

    public int testRoof(double aLen, double bLen) {
        RoofCalculator rc = new RoofCalculator();

        int r = rc.RoofCalc(aLen, bLen);

        return r;
    }

    public double testBand(double aLen, double bLen) {
        BandCalculator bc = new BandCalculator();

        double r = bc.bandCalc(aLen, bLen);

        return r;
    }

    public int testRaft(double aLen, double bLen) {
        RafterCalculator rac = new RafterCalculator();

        int r1 = rac.RaftCalc(aLen * 100.0, bLen * 100.0);
//        int r2 = rac.SpecialRaftCalc(aLen*100.0, bLen*100.0);

        return r1;
//        assertEquals(18,r2);
    }

    @Test
    public void test1() {
        double aLen = 7.8;
        double bLen = 3.7;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(28, roofArea);
        assertEquals(15, rafters);
        assertEquals(17.26, bandLength, 0.1);
        assertEquals(8, logs);
    }

    @Test
    public void test2() {
        double aLen = 5.2;
        double bLen = 4.1;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(21, roofArea);
        assertEquals(10, rafters);
        assertEquals(13.24, bandLength, 0.1);
        assertEquals(6, logs);
    }

    @Test
    public void test3() {
        double aLen = 3.2;
        double bLen = 5.11;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(16, roofArea);
        assertEquals(10, rafters);
        assertEquals(12.05, bandLength, 0.1);
        assertEquals(6, logs);
    }

    @Test
    public void test4() {
        double aLen = 6.2;
        double bLen = 2.4;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(14, roofArea);
        assertEquals(12, rafters);
        assertEquals(13.29, bandLength, 0.1);
        assertEquals(3, logs);
    }

    @Test
    public void test5() {
        double aLen = 8.2;
        double bLen = 4.11;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(33, roofArea);
        assertEquals(15, rafters);
        assertEquals(18.34, bandLength, 0.1);
        assertEquals(8, logs);
    }

    @Test
    public void test6() {
        double aLen = 11.3;
        double bLen = 7.9;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(89, roofArea);
        assertEquals(21, rafters);
        assertEquals(27.57, bandLength, 0.1);
        assertEquals(20, logs);
    }

    @Test
    public void test7() {
        double aLen = 10.0;
        double bLen = 3.3;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(33, roofArea);
        assertEquals(19, rafters);
        assertEquals(21.06, bandLength, 0.1);
        assertEquals(10, logs);
    }

    @Test
    public void test8() {
        double aLen = 2.2;
        double bLen = 8.9;
        int roofArea = testRoof(aLen, bLen);
        double bandLength = testBand(aLen, bLen);
        int rafters = testRaft(aLen, bLen);
        int logs = testLogs(aLen, bLen);

        assertEquals(19, roofArea);
        assertEquals(17, rafters);
        assertEquals(18.33, bandLength, 0.1);
        assertEquals(4, logs);
    }
}
