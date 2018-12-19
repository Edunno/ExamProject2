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
public class LogCalculator {
    
    private double spacing = 2.5;
 
    /**Handles the main calculation of the amount of logs needed.
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     * @return amount of logs
     */
    public int mainCalc(double dimensionA, double dimensionB){
        if(dimensionA<spacing){
            dimensionA = spacing;
        }
        if(dimensionB<spacing){
            dimensionB = spacing;
        }
        int logs = ((int) (dimensionA / spacing)+1) * ((int) (dimensionB/spacing)+1);
        return logs;
    }
    
    /**Allows for change in the desired spacing between logs.
     *
     * @param spacing distance in meters
     */
    public void setSpacing(double spacing){
        this.spacing = spacing;
    }
    
    /**Used for drawing mainly. Tells the amount of logs situated along the x-axis. Can also be used to derive the amount of logs on the y-axis.
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     * @return amount of logs on the length of the carport
     */
    public int getLogAmountsXSide(double dimensionA, double dimensionB){
        double dX;
        if(dimensionA > dimensionB){
            dX = dimensionA;
        }
        else{
            dX = dimensionB;
        }
        return ((int) (dX / spacing)+1);
    }
    
}
