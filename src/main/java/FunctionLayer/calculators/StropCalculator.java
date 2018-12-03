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
    
    private double a,b,spacing = 2.5;
    
    public StropCalculator(double dimensionA, double dimensionB) {
        a = dimensionA;
        b = dimensionB;
    }
    public int amount(){
        int res;
        if(a>b){
            res = (int) (b/spacing)+1;
        }
        else {
            res = (int) (a/spacing)+1;
        }
        return res;
    }
    
    public double length(){
        if(a>b){
            return a;
        }
        else{
            return b;
        }
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }
    
    
    
}
