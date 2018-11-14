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
    
    double aLen = 7.8;
    double bLen = 3.7;
    
    @Test
    public void testLogs() {
        LogCalculator lc = new LogCalculator();

        int r = lc.mainCalc(aLen, bLen);

        assertEquals(8, r);
    }

    @Test
    public void testRoof() {
        RoofCalculator rc = new RoofCalculator();

        int r = rc.RoofCalc(aLen, bLen);

        assertEquals(28, r);
    }

    @Test
    public void testBand() {
        BandCalculator bc = new BandCalculator();
        
        double r = bc.bandCalc(aLen, bLen);
        
        assertEquals(17.26,r,0.1);
    }
    
    @Test
    public void testRaft() {
        RafterCalculator rac = new RafterCalculator();
        
        int r1 = rac.RaftCalc(aLen, bLen);
        int r2 = rac.SpecialRaftCalc(aLen, bLen);
        
        assertEquals(16,r1);
        assertEquals(17,r2);
        
    }
}
