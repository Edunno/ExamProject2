/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.VisualRender;

import FunctionLayer.calculators.LogCalculator;
import FunctionLayer.calculators.SpecialRoofCalculator;

/**
 *
 * @author Esben
 */
public class FrontDrawer {

    private final double sizeX, sizeY;
    private final int svgX;
    private final int svgY;
    private final boolean isSpecial;
    private final int startCoords = 4;
    private final boolean hasShed;
    private String start;
    private final RectangleDrawer rd = new RectangleDrawer();
    private final LineDrawer ld = new LineDrawer();
    private final PolygonDrawer pd = new PolygonDrawer();
    private int height = 360;
    private int extraSpace = 0;
    private int roofH = 0;
    private double size = 50;

    /**
     * Starts the class that draws the front of the carport.
     *
     * @param sizeX the length of the carport in m.
     * @param sizeY the width of the carport in m.
     * @param isSpecial boolean deciding if the roof is flat or raised(i.e. special).
     * @param hasShed boolean deciding if the carport has a shed or not.
     */
    public FrontDrawer(double sizeX, double sizeY, boolean isSpecial, boolean hasShed) {
        if (sizeY < sizeX) {
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
        this.isSpecial = isSpecial;
        this.hasShed = hasShed;
    }

    /**
     * Takes an int that represents the slope of the raised roof, that the customer wants.
     *
     * @param slope the degrees of slope on the roof.
     */
    public void setSpecialMeasures(int slope) {
        if(isSpecial){
        SpecialRoofCalculator sr = new SpecialRoofCalculator(sizeX,sizeY,slope);
        this.roofH = (int)(sr.getHeightOfRoof()*100);
            extraSpace = roofH + 20; //This gives the drawing some extra height to fit in the raised roof in the drawing.
        }
    }

    /**
     * Sets the percentage size og the viewBox function in the <code>SVG</code> drawing. 50 is approximately normal size.
     *
     * @param s size where 50 is around normal size and 100 i double.
     */
    public void setSize(double s){
        this.size = s;
    }

    /**
     * This method initialises the calculators for getting the necessary information, on the parts for the carport, that needs to be included in the SVG drawing.
     * It also runs the methods that returns String objects that can be combined into the whole of the SVG String.
     *
     * @return String for use in HTML.
     */
    public String startDraw() {
        this.height += extraSpace;
//        start = "<SVG width=\"" + svgX * 1.1 + "\" height=\"" + height * 1.1 + "\">";
        start = "<SVG width=\""+size+"%\" viewBox=\"0 0 "+svgX+" "+height+"\">";
        start += drawGround();

        if (hasShed) {
            start += shedDrawer();
        }

        start += logDrawer();

        start += stropDrawer();

        if (isSpecial) {
            start += drawSpecial();
        } else {
            start += sternDrawer();
        }
        start += "</SVG>";

        return start;
    }

    private String drawGround() { //Draws the ground part of the front drawing
        rd.setFillCol("black");
        rd.setFillOp(0.1);
        String res = rd.RectangleDrawer(0, height - 80, 80, svgX);
        rd.setFillCol("white");
        rd.setFillOp(0);
        return res;
    }

    private String stropDrawer() {
        LogCalculator lc = new LogCalculator();
        int logDim = 6;
        int logSideY = lc.mainCalc(sizeX, sizeY) / lc.getLogAmountsXSide(sizeX, sizeY);
        String res = "";
        int localSvgX = svgX - 30 - startCoords;
        int startX = 15  + startCoords/2;
        for (int i = 0; i < logSideY; i++) {
            res += rd.RectangleDrawer(startX - (logDim / 2)+((localSvgX / (logSideY - 1))*i), startCoords + 18 + extraSpace, 18, logDim);
        }
        return res;
    }

    private String logDrawer() {
        LogCalculator lc = new LogCalculator();
        int logDim = 12;
        int logSideY = lc.mainCalc(sizeX, sizeY) / lc.getLogAmountsXSide(sizeX, sizeY);
        String res = "";
        rd.setFillOp(1);
        int localSvgX = svgX - 30 - startCoords;
        int startX = 15 + startCoords/2;
        for (int i = 0; i < logSideY; i++) {
            res += rd.RectangleDrawer(startX - (logDim / 2)+((localSvgX / (logSideY - 1))*i), startCoords + 27 + extraSpace, 300, logDim);
        }
        rd.setFillOp(0);
        return res;
    }

    private String shedDrawer() {
        int covDim = 20;
        int logDim = 12;
        String res = "";
        int i = startCoords + 12;
        ld.setStrokeBlack();
        while (i < svgX - startCoords - 12 - 30) {
            res += ld.LineDrawer(startCoords + (logDim) + i, startCoords + (logDim) + i, startCoords + extraSpace + 27, height - 80);
            i += covDim / 2;
        }
        LogCalculator lc = new LogCalculator();
        int logSideY = lc.mainCalc(sizeX, sizeY) / lc.getLogAmountsXSide(sizeX, sizeY);
        for (int j = 1; j < logSideY; j++) {
            res += rd.RectangleDrawer((svgX / logSideY) * j - (logDim / 2), height - 80, 50, logDim);
        }
        return res;
    }

    private String drawSpecial() { //Draws the raised roof of the carport, if this has been enabled
        int logDim = 18;
        int plankDim = 18;
        String res = "";
        int x = 0;
        while(x < svgX/2){
            res += ld.LineDrawer(startCoords+x, startCoords+x, extraSpace+startCoords+28-(int)(x*((double)(logDim+roofH)/(svgX/2))), extraSpace+startCoords+28);
            x += plankDim;
        }
        while(x < svgX-startCoords*2){
            res += ld.LineDrawer(startCoords+x, startCoords+x, extraSpace-roofH+logDim+(int)((x-svgX/2)*((double)(logDim+roofH)/(svgX/2))), extraSpace+startCoords+28);
            x += plankDim;
        }
        res += ld.LineDrawer(startCoords, svgX-startCoords*2, extraSpace+startCoords+28, extraSpace+startCoords+28);
        pd.setOp(1);
        res += pd.PolygonDrawer((svgX) / 2, 0 + (extraSpace - roofH), (svgX) / 2, logDim + (extraSpace - roofH), startCoords, extraSpace + startCoords + 28, startCoords, extraSpace - logDim + startCoords + 28);
        res += pd.PolygonDrawer((svgX) / 2, 0 + (extraSpace - roofH), (svgX) / 2, logDim + (extraSpace - roofH), svgX - startCoords, extraSpace + startCoords + 28, svgX - startCoords, extraSpace - logDim + startCoords + 28);
        return res;
    }

    private String sternDrawer() {
        rd.setFillOp(1);
        return rd.RectangleDrawer(startCoords - 2, startCoords + 2, 28, svgX - startCoords);
    }

}
