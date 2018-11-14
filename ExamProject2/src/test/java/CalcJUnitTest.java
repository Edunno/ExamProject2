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
}
