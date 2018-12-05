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
    private PolygonDrawer pd = new PolygonDrawer();
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
        if (isSpecial) {
            this.roofH = roofH;
            extraSpace = roofH + 20;
        }
    }

    public static void main(String[] args) {
        FrontDrawer fd = new FrontDrawer(6.3, 7.6, true, true);
        fd.setSpecialMeasures(60);
        System.out.println(fd.startDraw());
    }

    public String startDraw() {
        this.height += extraSpace;
        start = "<SVG width=\"" + svgX * 1.1 + "\" height=\"" + height * 1.1 + "\">";

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

    private String drawSpecial() {
        int logDim = 18;
        String res = "";
        int x = 0;
        while(x < svgX/2){
            res += ld.LineDrawer(startCoords+x, startCoords+x, extraSpace+startCoords+28-(int)(x*((double)(logDim+roofH)/(svgX/2))), extraSpace+startCoords+28);
            x += 20;
        }
        while(x < svgX){
            res += ld.LineDrawer(startCoords+x, startCoords+x, extraSpace-roofH+logDim+(int)((x-svgX/2)*((double)(logDim+roofH)/(svgX/2))), extraSpace+startCoords+28);
            x += 20;
        }
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
