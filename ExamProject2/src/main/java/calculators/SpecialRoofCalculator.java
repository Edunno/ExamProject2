/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

/**
 *
 * @author Dan
 */
public class SpecialRoofCalculator {

    int widthOfRoof;
    int rafterLenght;
    int slopeOfRoof;
    int midRoofDegrees;
    
    
    public SpecialRoofCalculator(int widthOfRoof, int slopeOfRoof){
        this.slopeOfRoof = slopeOfRoof;
        this.widthOfRoof = widthOfRoof;
    }

    public static void main(String[] args) {
        SpecialRoofCalculator src = new SpecialRoofCalculator(360, 20);
        src.calcMidRoofDegrees();
        src.calcRafterLength();
        
    }

    /**
     * This method calculates the middle angle og the roof, given the 2 other angles.
     * 
     * We know that a triangle is 180 degrees in total, and that the two bottom angles are always equal.
     * 
     * 
     * @return 
     */
    
    int calcMidRoofDegrees() {
        this.midRoofDegrees = 180 - (slopeOfRoof * 2);
        return midRoofDegrees;
    }
    
    /**
     * This method calculates the side of the roof lenght using sinus relations.
     * 
     * 
     * @return 
     */

    double calcRafterLength() {
        double AA = Math.toRadians(slopeOfRoof);
        double BB = Math.toRadians(midRoofDegrees);
        double bb = widthOfRoof;
        double aa = (Math.sin(AA) * bb) / (Math.sin(BB));
        this.rafterLenght = (int) aa;
        return aa;
    }

}
