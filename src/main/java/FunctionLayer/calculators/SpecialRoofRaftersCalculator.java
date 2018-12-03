/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
/**
 *
 * @author Esben
 */
public class SpecialRoofRaftersCalculator {
    private final double slopedGableLength;
    private double distance;
    private int amount;
    private SpecialRoofCalculator rc;
    
    public SpecialRoofRaftersCalculator(double lengthOfRoof, double widthOfRoof, double slopeOfRoof){
        rc = new SpecialRoofCalculator(lengthOfRoof,widthOfRoof,slopeOfRoof);
        slopedGableLength = rc.rafterLenght;
    }
    public int roofRaftCalc(){
        int approx = ((int) (slopedGableLength/0.335));
        double interim = slopedGableLength/approx;
        if(interim<0.335 && interim>0.280){
            distance = interim;
            amount = approx;
            return amount;
        }
        else{
            interim = slopedGableLength/(approx+1);
            if(interim<0.335 && interim>0.280){
                distance = interim;
                amount = approx+1;
                return amount;
            }
        }
        return 0;
    }

    public double getFlatDistance() {
        return (rc.widthOfRoof/2)/amount;
    }
    
    public double getDistance() {
        return distance;
    }
    
}
