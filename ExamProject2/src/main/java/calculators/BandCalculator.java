/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author Esben
 */
public class BandCalculator {
    
    public double bandCalc(double dimensionA, double dimensionB){
        double l = sqrt(pow(dimensionA,2)+pow(dimensionB,2))*2;
        return l;
    }
    
}
