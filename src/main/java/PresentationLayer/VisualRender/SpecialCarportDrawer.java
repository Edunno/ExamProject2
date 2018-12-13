/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.VisualRender;

import FunctionLayer.calculators.LogCalculator;
import FunctionLayer.calculators.RafterCalculator;
import FunctionLayer.calculators.SpecialRoofRaftersCalculator;
import FunctionLayer.calculators.StropCalculator;

/**
 *
 * @author Esben
 */
public class SpecialCarportDrawer {

    private final double sizeX, sizeY;
    private int svgX, svgY;
    private int shedSizeX, shedSizeY;
    private String start;
    private int startCoords = 4;
    private final RectangleDrawer rd = new RectangleDrawer();
    private boolean hasShed = false;
    private final double slope;
    private double xPercent = 1, yPercent = 1;

    /**
     * Initialises the class, and sets up the values that it uses internally.
     *
     * @param sizeX length in meters.
     * @param sizeY width in meters.
     * @param slope degrees of the roofs slope.
     */
    public SpecialCarportDrawer(double sizeX, double sizeY, double slope) {
        if (sizeY > sizeX) {
            this.sizeX = sizeY;
            this.sizeY = sizeX;
            this.svgX = (int) (sizeY * 100) + startCoords;
            this.svgY = (int) (sizeX * 100) + startCoords;
        } else {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.svgX = (int) (sizeX * 100) + startCoords;
            this.svgY = (int) (sizeY * 100) + startCoords;
        }
        this.slope = slope;
    }

    /**
     * Takes the input used for changing the size of the drawing.
     *
     * @param x change in size wanted. 1.0 is 100% size. 0.5 is 50% size.
     */
    public void setDrawSize(double x) { //Working 50% as intended. Cannot change size independently on different axis, so only x axis is enabled, and changes both x and y.
        this.xPercent = x;
        this.yPercent = x;
        this.startCoords = (int) (startCoords * x);
        this.svgX = (int) (((sizeX * 100) + startCoords) * x);
        this.svgY = (int) (((sizeY * 100) + startCoords) * x);
    }

    /**
     * sets the sizes of the shed. Doesn't decide whether there is a shed or
     * not.
     *
     * @param shedSizeX
     * @param shedSizeY
     */
    public void setShedSize(int shedSizeX, int shedSizeY) { //This method is not used, as to simplify the shed drawing, it is set to a default percantage size of the x value of the carport.
        this.shedSizeX = resizeX(shedSizeX);
        this.shedSizeY = resizeY(shedSizeY);
    }

    /**
     * Takes a Boolean that sets whether there will be drawn a shed or not.
     *
     * @param hasShed True if drawing should have a shed.
     */
    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }
    /**
     * This method initialises the calculators for getting the necessary information, on the parts for the carport, that needs to be included in the SVG drawing.
     * It also runs the methods that returns String objects that can be combined into the whole of the SVG String.
     *
     * @return String for use in HTML.
     */
    public String startDraw() {
        start = "<SVG width=\"" + svgX * 1.1 + "\" height=\"" + svgY * 1.1 + "\">";

        if (hasShed) {
            setShedSize((int) ((sizeX * 100) * 0.4), (int) ((sizeY * 100)) - 32);
            ShedDrawer sd = new ShedDrawer(svgX - shedSizeX, startCoords + resizeY(15), shedSizeX - startCoords - resizeX(15), shedSizeY + startCoords, resizeX(12));
            start += sd.mainDrawer();
        }

        start += sternDrawer();

        LogCalculator lc = new LogCalculator();
        int xSide = lc.getLogAmountsXSide(sizeX, sizeY);
        int allLogs = lc.mainCalc(sizeX, sizeY);
        start += logDrawer(xSide, allLogs);

        StropCalculator sc = new StropCalculator(sizeX, sizeY); // TODO Spacing selecter should be implemented into this class.
        int strops = sc.amount();
        int stropLength = (int) (sc.length() * 100);
        start += stropDrawer(strops, stropLength);

        RafterCalculator rc = new RafterCalculator();
        int rafts = rc.SpecialRaftCalc(sizeX, sizeY);
        int raftLength = (int) (rc.RaftLength(sizeX, sizeY) * 100);
        start += raftDrawer(rafts, raftLength);

        start += roofRidge();

        start += roofRafters();

        start += "</SVG>";
        return start;
    }

    private String raftDrawer(int rafts, int raftLength) {
        int logDim = resizeX(4);
        String res = "";
        for (int i = 0; i < rafts; i++) {
            if (i > 0) {
                res += rd.RectangleDrawer((svgX * i / (rafts - 1)) - logDim / 2, startCoords, resizeY(raftLength), logDim);
            } else {
                res += rd.RectangleDrawer(startCoords, startCoords, resizeY(raftLength), logDim);
            }
        }
        return res;
    }

    private String logDrawer(int xSide, int allLogs) {
        int logDimA = resizeX(12);
        int logDimB = resizeY(12);
        String res = "";
        int localSvgX = svgX - resizeX(30);
        int localSvgY = svgY - resizeY(30);
        int ySide = allLogs / xSide;
        int startY = resizeY(15) - (logDimB / 2) + startCoords;
        for (int i = 0; i < ySide; i++) {
            int startX = resizeX(15) - (logDimA / 2) + startCoords;
            for (int j = 0; j < xSide; j++) {
                res += rd.RectangleDrawer(startX, startY, logDimA, logDimB);
                startX += (localSvgX / (xSide - 1));
            }
            startY += (localSvgY / (ySide - 1));
        }
        return res;
    }

    private String stropDrawer(int strops, int stropLength) {
        int logDim = resizeX(6);
        String res = "";
        int localSvgY = svgY - resizeY(30);
        int startY = resizeY(15) - (logDim / 2) + startCoords;
        for (int i = 0; i < strops; i++) {
            res += rd.RectangleDrawer(startCoords, startY, logDim, resizeX(stropLength));
            startY += (localSvgY / (strops - 1));
        }
        return res;
    }

    private String sternDrawer() {
        int logDim = resizeX(2);
        String res = "";
        res += rd.RectangleDrawer(startCoords, startCoords - logDim, logDim, svgX - logDim);
        res += rd.RectangleDrawer(startCoords, svgY, logDim, svgX - logDim);
        res += rd.RectangleDrawer(startCoords - logDim, startCoords - logDim, svgY, logDim);
        res += rd.RectangleDrawer(svgX + logDim, startCoords - logDim, svgY, logDim);
        return res;
    }

    private String roofRidge() {
        String res = "";
        int logDim = resizeY(4);
        res += rd.RectangleDrawer(startCoords, (svgY / 2) - logDim / 2, logDim, svgX - 2);
//        res += rd.RectangleDrawer(startCoords, (svgY / 2) - logDim*2, logDim*4, svgX-2);
        return res;
    }

    private String roofRafters() {
        String res = "";
        int logDim = resizeY(2);
        SpecialRoofRaftersCalculator rrc = new SpecialRoofRaftersCalculator(sizeX, sizeY, slope);
        int amount = rrc.roofRaftCalc();
        int dist = (int) (rrc.getFlatDistance() * 100);
        dist = resizeX(dist);
        for (int i = 1; i < amount; i++) {
            res += rd.RectangleDrawer(startCoords, startCoords + i * dist - logDim / 2, logDim, svgX - 2);
        }
        for (int i = 1; i < amount; i++) {
            res += rd.RectangleDrawer(startCoords, svgY - startCoords - i * dist + logDim / 2, logDim, svgX - 2);
        }
        return res;
    }

    private int resizeX(int size) {
        if (((int) (size * xPercent)) >= 1) {
            return (int) (size * xPercent);
        }
        return 1;
    }

    private int resizeY(int size) {
        if (((int) (size * yPercent)) >= 1) {
            return (int) (size * yPercent);
        }
        return 1;
    }
}
