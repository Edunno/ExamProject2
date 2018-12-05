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

    double cLength;
    double cWidth;
    int cSlope;
    boolean hasShed;

    public Carport(double cLength, double cWidth, int cSlope, boolean hasShed) {
        this.cLength = cLength;
        this.cWidth = cWidth;
        this.cSlope = cSlope;
        this.hasShed = hasShed;
    }

    public double getcLength() {
        return cLength;
    }

    public void setcLength(double cLength) {
        this.cLength = cLength;
    }

    public double getcWidth() {
        return cWidth;
    }

    public void setcWidth(double cWidth) {
        this.cWidth = cWidth;
    }

    public int getcSlope() {
        return cSlope;
    }

    public void setcSlope(int cSlope) {
        this.cSlope = cSlope;
    }

    public boolean isHasShed() {
        return hasShed;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

}
