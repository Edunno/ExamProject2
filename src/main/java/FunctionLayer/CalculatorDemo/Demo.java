/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.CalculatorDemo;

import FunctionLayer.calculators.BandCalculator;
import FunctionLayer.calculators.LogCalculator;
import FunctionLayer.calculators.RafterCalculator;
import FunctionLayer.calculators.SpecialRoofCalculator;
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

        System.out.println("Insert length:");

        double length = sc.nextDouble();

        System.out.println("Insert width:");

        double width = sc.nextDouble();

        System.out.println("Do you want special roof? y/n");

        String yn = sc.next().toLowerCase();
        if (yn.equals("y")) {
            System.out.println("What slope, would you like on your roof? (max angle 45°)");
            double slope = sc.nextDouble();
            SpecialRoofCalculator src = new SpecialRoofCalculator(length, width, slope);
            System.out.println("Area of gable:" + " " + src.getAreaOfGable());
            System.out.println("Number of rafts:" + " " + rc.SpecialRaftCalc(length, width));
            System.out.println("Rafter length:" + " " + src.getRafterLenght());
            System.out.println("Slope of roof" + " " + src.getSlopeOfRoof());
            System.out.println("Height of roof" + " " + src.getHeightOfRoof());
            System.out.println("Area of roof" + " " + src.getAreaOfRoof());
            System.out.println("Width of roof" + " " + src.getWidthOfRoof());
            System.out.println("Length of roof" + " " + src.getLengthOfRoof());
            System.out.println("Length of bands:" + " " + bc.bandCalc(length, width));
            System.out.println("Number of logs:" + " " + lc.mainCalc(length, width));
        } else if (yn.equals("n")) {
            System.out.println("Length of bands:" + " " + bc.bandCalc(length, width));
            System.out.println("Number of logs:" + " " + lc.mainCalc(length, width));
            System.out.println("Number of rafts:" + " " + rc.RaftCalc(length, width));
            System.out.println("Length of rafts:" + " " + rc.RaftLength(length, width));

        }

    }

}
