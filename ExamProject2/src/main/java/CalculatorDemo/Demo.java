/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorDemo;

import calculators.BandCalculator;
import calculators.LogCalculator;
import calculators.RafterCalculator;
import calculators.SpecialRoofCalculator;
import java.util.Scanner;

/**
 *
 * @author caspe
 */
public class Demo {

    public static void main(String[] args) {

        BandCalculator bc = new BandCalculator();
        LogCalculator lc = new LogCalculator();
        RafterCalculator rc = new RafterCalculator();

        Scanner sc = new Scanner(System.in);

        System.out.println("Give length pls");

        double length = sc.nextDouble();

        System.out.println("Give me width pls");

        double width = sc.nextDouble();

        System.out.println("Number of bands:" + bc.bandCalc(length, width));
        System.out.println("Number of logs" + lc.mainCalc(length, width));
        System.out.println("Number of rafts:" + rc.RaftCalc(length, width));
        System.out.println("Length of rafts:" + rc.RaftLength(length, width));

        System.out.println("If special roof, number of rafts:" + rc.SpecialRaftCalc(length, width));

    }

}
