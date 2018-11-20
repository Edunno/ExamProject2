/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualRender;

import calculators.LogCalculator;
import calculators.RafterCalculator;

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
        BasicCarportDrawer bc = new BasicCarportDrawer(3.7, 6.8);
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
        this.start = "<SVG width=\"" + 700 + "\" height=\"" + 400 + "\">";

    }

    public void setDrawSize(int x, int y) { //Not working as intended yet
        this.svgX = x;
        this.svgY = y;
        this.start = "<SVG width=\"" + svgX + "\" height=\"" + svgY + "\">";
    }

    public String startDraw() {
        LogCalculator lc = new LogCalculator();
        int xSide = lc.getLogAmountsXSide(sizeX, sizeY);
        int allLogs = lc.mainCalc(sizeX, sizeY);
        start += logDrawer(xSide, allLogs);
        RafterCalculator rc = new RafterCalculator();
        int rafts = rc.RaftCalc(sizeX, sizeY);
        int raftLength = (int) sizeX*100;
        start += raftDraw(rafts, raftLength);

        start += "</SVG>";
        return start;
    }

    private String raftDraw(int rafts, int raftLength) {
        String res = "";
        int startX = 0;
        for (int i = 0; i < rafts; i++) {
            res += rd.RectangleDrawer(startX, 0, raftLength, 4);
            startX += (svgX / (rafts-1));
        }
        return res;
    }

    private String logDrawer(int xSide, int allLogs) {
        String res = "";
        int ySide = allLogs/xSide;
        int startY = 0;
        for(int i = 0; i < ySide;i++){
            int startX = 0;
            for(int j = 0; j < xSide;j++){
                res +=rd.RectangleDrawer(startX, startY, 6, 6);
                startX += (svgX/(xSide-1))-3;
            }
            startY += svgY/(ySide-1);
        }
        return res;
    }
}
