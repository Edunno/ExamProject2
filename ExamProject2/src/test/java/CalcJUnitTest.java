/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import calculators.BandCalculator;
import calculators.LogCalculator;
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
    
    @Test
    public void testLogs() {
        LogCalculator lc = new LogCalculator();
        double aLen = 7.8;
        double bLen = 3.7;

        int r = lc.mainCalc(aLen, bLen);

        assertEquals(8, r);
    }

    @Test
    public void testRoof() {
        RoofCalculator rc = new RoofCalculator();
        double aLen = 7.8;
        double bLen = 3.7;

        int r = rc.RoofCalc(aLen, bLen);

        assertEquals(28, r);
    }

    @Test
    public void testBand() {
        BandCalculator bc = new BandCalculator();
        double aLen = 7.8;
        double bLen = 3.7;
        
        double r = bc.bandCalc(aLen, bLen);
        
        assertEquals(17.26,r,0.1);
    }
}
