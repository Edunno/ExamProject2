/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.VisualRender;

import FunctionLayer.calculators.LogCalculator;

/**
 *
 * @author Esben
 */
public class FrontDrawer {

    private final double sizeX, sizeY;
    private int svgX, svgY;
    private final boolean isSpecial;
    private int startCoords = 4;
    private final boolean hasShed;
    private String start;
    private RectangleDrawer rd = new RectangleDrawer();
    private LineDrawer ld = new LineDrawer();
    private int height = 360;
    private int extraSpace = 0;
    private int roofH = 0;

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

    public void setSpecialMeasures(int roofH) {
        this.roofH = roofH;
        extraSpace = roofH + 20;
    }

    public static void main(String[] args) {
        FrontDrawer fd = new FrontDrawer(4.3, 7.6, true, true);
        fd.setSpecialMeasures(60);
        System.out.println(fd.startDraw());
    }

    public String startDraw() {
        this.height += extraSpace;
        start = "<SVG width=\"" + svgX * 1.1 + "\" height=\"" + height * 1.1 + "\">";

        start += drawGround();

        start += stropDrawer();

        start += logDrawer();

//        if (hasShed) {
//            start += shedDrawer();
//        }
//
//        if (isSpecial) {
//            start += drawSpecial();
//        } else {
//            start += sternDrawer();
//        }
        start += "</SVG>";

        return start;
    }

    private String drawGround() {
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
        int startX = 15 - (logDim / 2) + startCoords;
        for (int i = 0; i < logSideY; i++) {
            res += rd.RectangleDrawer(startX-(logDim), startCoords + 18 + extraSpace, 18, logDim);
            startX += (localSvgX / (logSideY - 1));
        }
        return res;
    }

    private String logDrawer() {
        LogCalculator lc = new LogCalculator();
        int logDim = 12;
        int logSideY = lc.mainCalc(sizeX, sizeY) / lc.getLogAmountsXSide(sizeX, sizeY);
        String res = "";
        int localSvgX = svgX - 30 - startCoords;
        int startX = 15 - (logDim / 2) + startCoords;
        for (int i = 0; i < logSideY; i++) {
            res += rd.RectangleDrawer(startX-(logDim/2), startCoords + 27 + extraSpace, 300, logDim);
            startX += (localSvgX / (logSideY - 1));
        }
        return res;
    }

    private String shedDrawer() {
        int covDim = 20;
        int LogDim = 12;
        String res = "";
        int i = startCoords+12;
        while(i < svgX-startCoords-12){
            
        }
        return res;
    }

    private String drawSpecial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String sternDrawer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
