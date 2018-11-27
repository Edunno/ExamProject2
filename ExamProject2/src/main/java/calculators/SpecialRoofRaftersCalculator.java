/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
/**
 *
 * @author Esben
 */
public class SpecialRoofRaftersCalculator {
    private final double slopedGableLength;
    
    
    public SpecialRoofRaftersCalculator(double lengthOfRoof, double widthOfRoof, double slopeOfRoof){
        SpecialRoofCalculator rc = new SpecialRoofCalculator(lengthOfRoof,widthOfRoof,slopeOfRoof);
        slopedGableLength = rc.rafterLenght;
    }
    public int roofRaftCalc(){
        int res = (int) (slopedGableLength/0.307);
        return res;
    }
}
