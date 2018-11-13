/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import static java.lang.Math.*;

/**
 *
 * @author Esben
 */
public class BandCalculator {
    
//        For test only:
//    public static void main(String[] args) {
//        BandCalculator bc = new BandCalculator();
//        double l = bc.bandLength(3.1,7.3);
//        System.out.println(l);
//    }
    
    public double bandLength(double a, double b){
        double l = sqrt(pow(a,2)+pow(b,2))*2;
        return l;
    }
}
