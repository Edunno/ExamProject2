/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualRender;

import calculators.LogCalculator;
import calculators.RafterCalculator;
import calculators.StropCalculator;

/**
 *
 * @author Esben
 */
public class BasicCarportDrawer {

    private final double sizeX, sizeY;
    private final int svgX, svgY;
    private int shedSizeX, shedSizeY;
    private String start;
    private int startCoords = 4;
    private RectangleDrawer rd = new RectangleDrawer();
    private boolean hasShed = false;

    public static void main(String[] args) {
        double x = 5.0;
        double y = 7.7;
        BasicCarportDrawer bc = new BasicCarportDrawer(x, y);
        bc.setHasShed(true);
        System.out.println(bc.startDraw());
    }

    public BasicCarportDrawer(double sizeX, double sizeY) {
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
        this.start = "<SVG width=\"" + svgX * 1.1 + "\" height=\"" + svgY * 1.1 + "\">";

    }

    public void setDrawSize(int x, int y) { //Not working as intended yet
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public void setShedSize(int shedSizeX, int shedSizeY) {
        this.shedSizeX = shedSizeX;
        this.shedSizeY = shedSizeY;
    }

    public String startDraw() {
        setShedSize((int) ((sizeX * 100) * 0.4),(int) ((sizeY * 100))-32);
        if (hasShed) {

            ShedDrawer sd = new ShedDrawer(svgX - shedSizeX, startCoords+18, shedSizeX-15, shedSizeY, 12);
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
        int rafts = rc.RaftCalc(sizeX, sizeY);
        int raftLength = (int) (rc.RaftLength(sizeX, sizeY) * 100);
        start += raftDrawer(rafts, raftLength);

        if (hasShed) {
            BandDrawer bd = new BandDrawer(startCoords, svgX - shedSizeX, svgY);
            start += bd.drawBand();
        } else {
            BandDrawer bd = new BandDrawer(startCoords, svgX, svgY);
            start += bd.drawBand();
        }
        start += "</SVG>";
        return start;
    }

    private String raftDrawer(int rafts, int raftLength) {
        int logDim = 4;
        String res = "";
        for (int i = 0; i < rafts; i++) {
            if (i > 0) {
                res += rd.RectangleDrawer((svgX * i / (rafts - 1)) - logDim / 2, startCoords, raftLength, logDim);
            } else {
                res += rd.RectangleDrawer(startCoords, startCoords, raftLength, logDim);
            }
        }
        return res;
    }

    private String logDrawer(int xSide, int allLogs) {
        int logDimA = 12;
        int logDimB = 12;
        String res = "";
        int localSvgX = svgX - 30;
        int localSvgY = svgY - 30;
        int ySide = allLogs / xSide;
        int startY = 15 - (logDimB / 2) + startCoords;
        for (int i = 0; i < ySide; i++) {
            int startX = 15 - (logDimA / 2) + startCoords;
            for (int j = 0; j < xSide; j++) {
                res += rd.RectangleDrawer(startX, startY, logDimA, logDimB);
                startX += (localSvgX / (xSide - 1));
            }
            startY += (localSvgY / (ySide - 1));
        }
        return res;
    }

    private String stropDrawer(int strops, int stropLength) {
        int logDim = 6;
        String res = "";
        int localSvgY = svgY - 30;
        int startY = 15 - (logDim / 2) + startCoords;
        for (int i = 0; i < strops; i++) {
            res += rd.RectangleDrawer(startCoords, startY, logDim, stropLength);
            startY += (localSvgY / (strops - 1));
        }
        return res;
    }

    private String sternDrawer() {
        int logDim = 2;
        String res = "";
        res += rd.RectangleDrawer(startCoords, startCoords - logDim, logDim, svgX - logDim);
        res += rd.RectangleDrawer(startCoords, svgY, logDim, svgX - logDim);
        res += rd.RectangleDrawer(startCoords - logDim, startCoords - logDim, svgY, logDim);
        res += rd.RectangleDrawer(svgX + logDim, startCoords - logDim, svgY, logDim);
        return res;
    }
}
