/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

/**
 *
 * @author Esben
 */
public class LogCalculator {
    
    private double spacing = 2.5;
 
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
    
    public void setSpacing(double spacing){
        this.spacing = spacing;
    }
    
    public int getLogAmountsXSide(double dimensionA, double dimensionB){
        double dX = 0;
        if(dimensionA > dimensionB){
            dX = dimensionA;
        }
        else{
            dX = dimensionB;
        }
        return ((int) (dX / spacing)+1);
    }
    
}
