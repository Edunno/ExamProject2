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
public class StropCalculator {
    
    final private double a,b,c;
    
    public StropCalculator(double dimensionA, double dimensionB, double spacing) {
        a = dimensionA;
        b = dimensionB;
        c = spacing;
    }
    public int amount(){
        int res;
        if(a>b){
            res = (int) (b/c)+1;
        }
        else {
            res = (int) (a/c)+1;
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
    
}
