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
 
    public int mainCalc(double a, double b){
        if(a<2.5){
            a = 2.5;
        }
        if(b<2.5){
            b = 2.5;
        }
        int logs = ((int) (a / 2.5)+1) * ((int) (b/2.5)+1);
        return logs;
    }
}
