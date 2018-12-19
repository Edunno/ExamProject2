/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

/**
 *
 * @author casper
 */
public class RoofCalculator {
    
    /**Calculates the area of the roof.
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     * @return area of roof, in meters squared
     */
    public double RoofCalc (double dimensionA, double dimensionB){
        double rc = dimensionA * dimensionB;
        return rc;
    }
    
}
