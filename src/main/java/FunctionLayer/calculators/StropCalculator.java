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
public class StropCalculator {

    private final double dimensionA, dimensionB;
    private double spacing = 2.5;

    /**Contains methods for strop calculation
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     */
    public StropCalculator(double dimensionA, double dimensionB) {
        this.dimensionA = dimensionA;
        this.dimensionB = dimensionB;
    }

    /**Calculates the amount of strops needed
     *
     * @return amount of strops
     */
    public int amount() {
        int res;
        if (dimensionA > dimensionB) {
            res = (int) (dimensionB / spacing) + 1;
        } else {
            res = (int) (dimensionA / spacing) + 1;
        }
        return res;
    }

    /**Calculates the length of the strops for the carport
     *
     * @return length in meters
     */
    public double length() {
        if (dimensionA > dimensionB) {
            return dimensionA;
        } else {
            return dimensionB;
        }
    }

    /**Changes the spacing between the strops
     *
     * @param spacing distance in meters
     */
    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }

}
