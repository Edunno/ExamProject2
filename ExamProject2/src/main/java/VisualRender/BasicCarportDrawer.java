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

    private double sizeX, sizeY;
    private int svgX, svgY;
    private int shedSizeX, shedSizeY = 0;
    private String start;
    private int startCoords = 4;
    private RectangleDrawer rd = new RectangleDrawer();
    private LineDrawer ld = new LineDrawer();
    private boolean hasShed = false;
    private double xPercent, yPercent = 1;

    public static void main(String[] args) {
        double x = 3.0;
        double y = 5.7;
        BasicCarportDrawer bc = new BasicCarportDrawer(x, y);
        bc.setDrawSize(1.3);
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
    }

    public void setDrawSize(double x) { //Not working as intended yet
        this.xPercent = x;
        this.startCoords = (int) (startCoords * x);
        this.svgX = (int) (((sizeX * 100) + startCoords) * x);
        this.svgY = (int) (((sizeY * 100) + startCoords) * x);
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public void setShedSize(int shedSizeX, int shedSizeY) {
        this.shedSizeX = resizeX(shedSizeX);
        this.shedSizeY = resizeY(shedSizeY);
    }

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
        int rafts = rc.RaftCalc(sizeX, sizeY);
        int raftLength = (int) (rc.RaftLength(sizeX, sizeY) * 100);
        start += raftDrawer(rafts, raftLength);

        if (hasShed) {
            start += drawBand();
        } else {
            start += drawBand();
        }
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
        private String drawBand() {
        String res = "";
        ld.setIsDotted(true);
        res += ld.LineDrawer(startCoords + resizeY(15), svgX-shedSizeX - resizeX(15), startCoords + resizeX(15), svgY - resizeY(15));
        res += ld.LineDrawer(startCoords + resizeY(15), svgX-shedSizeX - resizeX(15), svgY - resizeY(15), startCoords + resizeX(15));
        return res;
    }

    private int resizeX(int size) {
        if(((int)(size*xPercent))>=1){
        return (int) (size * xPercent);
        }
        return 1;
    }

    private int resizeY(int size) {
        if(((int)(size*yPercent))>=1){
        return (int) (size * yPercent);
        }
        return 1;
    }
}
