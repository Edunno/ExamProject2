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
public class SideDrawer {

    private final double sizeX, sizeY;
    private int svgX, svgY;
    private boolean isSpecial;
    private int startCoords = 4;
    private boolean hasShed;
    private String start;
    private RectangleDrawer rd = new RectangleDrawer();
    private LineDrawer ld = new LineDrawer();
    private int height = 360;
    private int extraSpace = 0;
    private int roofH = 0;

    public SideDrawer(double sizeX, double sizeY, boolean isSpecial, boolean hasShed) {
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
        this.isSpecial = isSpecial;
        this.hasShed = hasShed;
    }

    public void setSpecialMeasures(int roofH) {
        this.roofH = roofH;
        extraSpace = roofH+20;
    }

    public static void main(String[] args) {
        SideDrawer sd = new SideDrawer(3.4, 7.6, true, true);
        sd.setSpecialMeasures(60);
        System.out.println(sd.startDraw());
    }

    public String startDraw() {
        this.height += extraSpace;
        start = "<SVG width=\"" + svgX * 1.1 + "\" height=\"" + height * 1.1 + "\">";

        start += drawGround();

        start += stropDrawer();

        start += logDrawer();

        if (hasShed) {
            start += shedDrawer();
        }

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
        return rd.RectangleDrawer(startCoords, startCoords + 18 + extraSpace, 18, svgX - startCoords * 2);
    }

    private String logDrawer() {
        LogCalculator lc = new LogCalculator();
        int logDim = 12;
        String res = "";
        int localSvgX = svgX - 30 - startCoords - (logDim / 2);
        int startX = 15 - (logDim / 2) + startCoords;
        int xSide = lc.getLogAmountsXSide(sizeX, sizeY);
        for (int i = 0; i < xSide; i++) {
            res += rd.RectangleDrawer(startX, startCoords + 27 + extraSpace, 300, logDim);
            startX += (localSvgX / (xSide - 1));
        }
        return res;
    }

    private String shedDrawer() {
        int shedStartX = (int) (svgX - (svgX * 0.4));
        int covDim = 20;
        int logDim = 12;
        String res = "";
        res += rd.RectangleDrawer(shedStartX - (logDim / 2), startCoords + 27 + extraSpace, 300, logDim);
        int shedCoverLength = svgX - shedStartX - startCoords - 15 - (logDim / 2);
        int i = 0;
        ld.setStrokeBlack();
        while (i < shedCoverLength / (covDim / 2)) {
            res += ld.LineDrawer(shedStartX + (logDim / 2) + ((covDim / 2) * i), shedStartX + (logDim / 2) + ((covDim / 2) * i), startCoords + 18 + 18 + extraSpace, height - 80);
            i++;
        }
        int j = 0;
        int k = 1;
        int logAmount = shedCoverLength / 125;
        while (j < shedCoverLength) {
            res += rd.RectangleDrawer(shedStartX + logDim + (shedCoverLength / logAmount * k), height - 80, 50, logDim);
            k++;
            j = shedCoverLength / logAmount * (k + 1);
        }
        return res;
    }

    private String sternDrawer() {
        rd.setFillOp(1);
        return rd.RectangleDrawer(startCoords - 2, startCoords + 2, 28, svgX - startCoords);
    }

    private String drawSpecial() {
        int sternDim = 2;
        String res = "";
        res += rd.RectangleDrawer(startCoords - sternDim, 0 + (extraSpace - roofH), 28 + startCoords + 2 + roofH, sternDim);
        res += rd.RectangleDrawer(startCoords, 2 + (extraSpace - roofH), 28 + startCoords + roofH, svgX - startCoords - sternDim);
        res += rd.RectangleDrawer(svgX - sternDim, 0 + (extraSpace - roofH), 28 + startCoords + 2 + roofH, sternDim);
        return res;
    }

}
