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
public class ShedAndBandDrawer {

    private int shedSizeX, shedSizeY, startX, startY, svgX, svgY, logDim, startCoords;
    private boolean withBand, makeShed;
    private String res = "";
    private LineDrawer ld = new LineDrawer();

    public void ShedAndBandDrawer(int shedSizeX, int shedSizeY, int startX, int startY, int svgX, int svgY, int logDim, int startCoords, boolean withBand, boolean makeShed) {
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

    private String drawBand() {
        String res = "";
        if (makeShed) {
            res += ld.LineDrawer(startCoords + 15, svgX - shedSizeX, startCoords + 15, svgY - shedSizeY);
        } else {
            res += ld.LineDrawer(startCoords + 15, svgX - 15, startCoords + 15, svgY - 15);
        }
        return res;
    }

}
