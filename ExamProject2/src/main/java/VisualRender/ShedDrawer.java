/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualRender;

/**
 *
 * @author Esben
 */
public class ShedDrawer {

    private int shedSizeX, shedSizeY, startX, startY, svgX, svgY, logDim, startCoords;
    private boolean withBand, makeShed;
    private String res = "";
    private LineDrawer ld = new LineDrawer();

    public ShedDrawer(int shedSizeX, int shedSizeY, int startX, int startY, int svgX, int svgY, int logDim, int startCoords, boolean withBand, boolean makeShed) {
        this.shedSizeX = shedSizeX;
        this.shedSizeY = shedSizeY;
        this.startX = startX;
        this.startY = startY;
        this.svgX = svgX;
        this.svgY = svgY;
        this.logDim = logDim;
        this.startCoords = startCoords;
        this.withBand = withBand;
        this.makeShed = makeShed;
    }
    public String mainDrawer(){
                if (withBand) {
            drawBand();
        }
        if (makeShed) {

        }
        return res;
    }


}
