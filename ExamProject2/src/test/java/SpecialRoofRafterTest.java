/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import calculators.SpecialRoofRaftersCalculator;

/**
 *
 * @author Esben
 */
public class SpecialRoofRafterTest {

    @Test
    public void test1() {
        SpecialRoofRaftersCalculator rrc = new SpecialRoofRaftersCalculator(3, 5.6, 30);
        assertEquals(4,rrc.roofRaftCalc());
    }

    @Test
    public void test2() {
        SpecialRoofRaftersCalculator rrc = new SpecialRoofRaftersCalculator(6, 3.6, 39);
        assertEquals(7, rrc.roofRaftCalc());
    }

    @Test
    public void test3() {
        SpecialRoofRaftersCalculator rrc = new SpecialRoofRaftersCalculator(5, 5, 39);
        assertEquals(10, rrc.roofRaftCalc());
    }

    @Test
    public void test4() {
        SpecialRoofRaftersCalculator rrc = new SpecialRoofRaftersCalculator(4.5, 4, 39);
        assertEquals(8, rrc.roofRaftCalc());
    }

    @Test
    public void test5() {
        SpecialRoofRaftersCalculator rrc = new SpecialRoofRaftersCalculator(6, 2.8, 28);
        assertEquals(5, rrc.roofRaftCalc());
    }
}
