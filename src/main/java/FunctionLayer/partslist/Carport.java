/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.partslist;

/**
 *
 * @author Dan
 */
public class Carport {

    double cLength; // The length of the carport in meters
    double cWidth; // The width of the carport in meters
    int cSlope; // The slope for the roof, if this is 0 it is treated as a flat roof
    boolean hasShed; // If the carport has a shed

    public Carport(double cLength, double cWidth, int cSlope, boolean hasShed) {
        this.cLength = cLength;
        this.cWidth = cWidth;
        this.cSlope = cSlope;
        this.hasShed = hasShed;
    }

    /**
     * Returns the length of the carport in meters
     *
     * @return length in meters
     */

    public double getcLength() {
        return cLength;
    }

    /**
     * Sets the parameter as the carport length
     *
     * @param cLength length of the carport in meters
     */
    public void setcLength(double cLength) {
        this.cLength = cLength;
    }

    /**
     * Returns the width of the carport in meters
     *
     * @return width in meters
     */
    public double getcWidth() {
        return cWidth;
    }

    /**
     * Sets the parameter as the carport width
     *
     * @param cWidth width of the carport in meters
     */
    public void setcWidth(double cWidth) {
        this.cWidth = cWidth;
    }

    /**
     * Returns the slope of the roof on the carport
     *
     * @return slope in degrees
     */
    public int getcSlope() {
        return cSlope;
    }

    /**
     * Sets the slope of the roof on the carport
     *
     * @param cSlope is the slope of the roof in degrees
     */
    public void setcSlope(int cSlope) {
        this.cSlope = cSlope;
    }

    /**
     * Returns true if the carport has a shed
     *
     * @return boolean for shed
     */
    public boolean isHasShed() {
        return hasShed;
    }

    /**
     * Set true if the carport has a shed
     *
     * @param hasShed if there is a shed this should be true
     */
    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

}
