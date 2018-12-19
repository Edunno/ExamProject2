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
public class ShedCalculator {

private double dimensionA, dimensionB;
private double spacing = 2.5;
private int logs = 0;
private double mOfWall = 0;
private double mOfWallSupport = 0;

    /**Contatins methods for calculating materials for sheds.
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     */
    public ShedCalculator(double dimensionA, double dimensionB) {
        this.dimensionA = dimensionA;
        this.dimensionB = dimensionB;
        this.spacing = spacing/2;
        calcWall();
        calcExtraLogs();
    }

    private void calcWall() {
        double wallA = dimensionA*1.125;
        double wallB = dimensionB*1.125;
        mOfWall += wallA/0.10*2 + wallB/0.10*2;
        mOfWallSupport += dimensionA*4+dimensionB*4+2; //Two extra meters for door support
    }

    private void calcExtraLogs() {
        logs += (int) (dimensionA/spacing+1+dimensionB/spacing+1)-2;
    }

    /**Returns logs needed
     *
     * @return log amount
     */
    public int getLogs() {
        return logs;
    }

    /**Returns the meters of wall needed. Notice this is in vertical meters, expecting boards to be 0.1 meters wide.
     *
     * @return meters length of boards needed
     */
    public double getmOfWall() {
        return mOfWall;
    }

    /**Gets the length of wood needed for support to hold up the boards for the wall.
     *
     * @return meters of wood
     */
    public double getmOfWallSupport() {
        return mOfWallSupport;
    }

    /**returns the value of how far the space between logs in the shed are.
     *
     * @return log spacing in meters
     */
    public double getSpacing() {
        return spacing;
    }

    /**Sets what distance the space beetween logs in the shed have.
     *
     * @param spacing in meters
     */
    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }
    
    
    
}
