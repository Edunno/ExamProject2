/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

/**
 *
 * @author Dan
 */
public class SpecialRoofCalculator {

    double widthOfRoof;
    double lengthOfRoof;
    double slopeOfRoof;
    double rafterLenght;
    double midRoofDegrees;
    double areaOfRoof;
    double heightOfRoof;
    double areaOfGable;

    public SpecialRoofCalculator(double lengthOfRoof, double widthOfRoof, double slopeOfRoof) {
        this.slopeOfRoof = slopeOfRoof;

        if (widthOfRoof < lengthOfRoof || widthOfRoof == lengthOfRoof) {
            this.widthOfRoof = widthOfRoof;
            this.lengthOfRoof = lengthOfRoof;
        } else {
            this.widthOfRoof = lengthOfRoof;
            this.lengthOfRoof = widthOfRoof;
        }

        this.midRoofDegrees = calcMidRoofDegrees();
        this.rafterLenght = calcRafterLength();
        this.areaOfRoof = calcAreaOfRoof();
        this.heightOfRoof = calcHeightOfRoof();
        this.areaOfGable = calcAreaOfGable();
    }

    /**
     * Calculates the middle angle og the roof, given the 2 other angles.
     *
     *
     * We know that a triangle is 180 degrees in total, and that the two bottom
     * angles are always equal.
     *
     * @return the degrees of the upper angle and sets it on the object
     */
    double calcMidRoofDegrees() {
        double mrd = 180 - (slopeOfRoof * 2);
        return mrd;
    }

    /**
     * Calculates the side of the roof lenght using sinus relations.
     *
     *
     * @return the lenght of the rafters, also sets it on the object
     */
    double calcRafterLength() {
        double AA = Math.toRadians(slopeOfRoof);
        double BB = Math.toRadians(midRoofDegrees);
        double bb = widthOfRoof;
        double aa = (Math.sin(AA) * bb) / (Math.sin(BB));
        return aa;
    }

    /**
     * Calculates the total area of the roof using the rafter lenght and the
     * lenght of the roof
     *
     * @return
     */
    double calcAreaOfRoof() {
        double aor = rafterLenght * lengthOfRoof * 2;
        return aor;
    }

    /**
     * Calculates the height of the roof using the Pythagorean theorem
     *
     * @return
     */
    double calcHeightOfRoof() {
        double height;
        height = Math.pow(rafterLenght, 2) - Math.pow((widthOfRoof / 2), 2);
        height = Math.sqrt(height);
        return height;
    }

    /**
     * Calculates the area of the gable
     *
     * @return
     */
    double calcAreaOfGable() {
        double aog = 0.5 * heightOfRoof * widthOfRoof * 2;
        return aog;
    }

    public double getWidthOfRoof() {
        return widthOfRoof;
    }

    public double getLengthOfRoof() {
        return lengthOfRoof;
    }

    public double getSlopeOfRoof() {
        return slopeOfRoof;
    }

    public double getRafterLenght() {
        return rafterLenght;
    }

    public double getMidRoofDegrees() {
        return midRoofDegrees;
    }

    public double getAreaOfRoof() {
        return areaOfRoof;
    }

    public double getHeightOfRoof() {
        return heightOfRoof;
    }

    public double getAreaOfGable() {
        return areaOfGable;
    }

}
