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
    private int svgX;
    private int svgY;
    private String start;
    RectangleDrawer rd = new RectangleDrawer();

    public static void main(String[] args) {
        BasicCarportDrawer bc = new BasicCarportDrawer(5.5, 6.8);
        System.out.println(bc.startDraw());
    }

    public BasicCarportDrawer(double sizeX, double sizeY) {
        if (sizeY > sizeX) {
            this.sizeX = sizeY;
            this.sizeY = sizeX;
            this.svgX =(int) (sizeY * 100);
            this.svgY =(int) (sizeX * 100);
        } else {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.svgX =(int) (sizeX * 100);
            this.svgY =(int) (sizeY * 100);
        }
        this.start = "<SVG width=\"" + 800 + "\" height=\"" + 600 + "\">";

    }

    public void setDrawSize(int x, int y) { //Not working as intended yet
        this.svgX = x;
        this.svgY = y;
        this.start = "<SVG width=\"" + svgX + "\" height=\"" + svgY + "\">";
    }

    public String startDraw() {
        StropCalculator sc = new StropCalculator(sizeX, sizeY, 2.5); // TODO Spacing selecter should be implemented into this class.
        int strops = sc.amount();
        int stropLength = (int) (sc.length()*100);
        start += stropDrawer(strops, stropLength);
        LogCalculator lc = new LogCalculator();
        int xSide = lc.getLogAmountsXSide(sizeX, sizeY);
        int allLogs = lc.mainCalc(sizeX, sizeY);
        start += logDrawer(xSide, allLogs);
        RafterCalculator rc = new RafterCalculator();
        int rafts = rc.RaftCalc(sizeX, sizeY);
        int raftLength = (int) (rc.RaftLength(sizeX, sizeY)*100);
        start += raftDrawer(rafts, raftLength);

        start += "</SVG>";
        return start;
    }

    private String raftDrawer(int rafts, int raftLength) {
        int logDim = 4;
        String res = "";
        int startX = logDim/2;
        for (int i = 0; i < rafts; i++) {
            res += rd.RectangleDrawer(startX-logDim/2, 0, raftLength, logDim);
            startX += svgX / (rafts-1);
        }
        return res;
    }

    private String logDrawer(int xSide, int allLogs) {
        int logDimA = 8;
        int logDimB = 8;
        String res = "";
        int localSvgX = svgX-30;
        int localSvgY = svgY-30;
        int ySide = allLogs/xSide;
        int startY = 15-(logDimB/2);
        for(int i = 0; i < ySide;i++){
            int startX = 15-(logDimA/2);
            for(int j = 0; j < xSide;j++){
                res +=rd.RectangleDrawer(startX, startY, logDimA, logDimB);
                startX += (localSvgX/(xSide-1))-(logDimA/2);
            }
            startY += (localSvgY/(ySide-1))+(logDimB/2);
        }
        return res;
    }

    private String stropDrawer(int strops, int stropLength) {
        int logDim = 6;
        String res = "";
        int localSvgY = svgY-30;
        int startY = 15-(logDim/2);
        for(int i = 0; i < strops;i++){
            res += rd.RectangleDrawer(0, startY, logDim, stropLength);
            startY += (localSvgY/(strops-1))+(logDim/2);
        }
        return res;
    }
}
