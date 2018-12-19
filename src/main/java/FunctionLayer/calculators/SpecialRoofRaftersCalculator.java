/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;
/**
 *
 * @author Esben
 */
public class SpecialRoofRaftersCalculator {
    private final double slopedGableLength;
    private double distance;
    private int amount;
    private SpecialRoofCalculator rc;
    
    /**Constructor for calculating rafters atop the slanted roof
     *
     * @param lengthOfRoof Length of roof in meters
     * @param widthOfRoof Width of roof in meters
     * @param slopeOfRoof slope of roof in degrees
     */
    public SpecialRoofRaftersCalculator(double lengthOfRoof, double widthOfRoof, double slopeOfRoof){
        rc = new SpecialRoofCalculator(lengthOfRoof,widthOfRoof,slopeOfRoof);
        slopedGableLength = rc.rafterLenght;
    }

    /**Main method for calculating rafters. It ensures that tiles will not overlap too much, or not at all.
     *
     * @return amount of rafters
     */
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

    /**Used for drawing, as the flat distance is required dor spacing the rafters properly
     *
     * @return the flat width in meters
     */
    public double getFlatDistance() {
        return (rc.widthOfRoof/2)/amount;
    }
    
    /**Originally used for drawing, now possibly for use in later stage.
     *
     * @return spacing between rafters in meters
     */
    public double getDistance() {
        return distance;
    }
    
}
