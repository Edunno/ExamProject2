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
public class BandDrawer {

    int startCoords, svgX, svgY;
    LineDrawer ld = new LineDrawer();

    public BandDrawer(int startCoords, int svgX, int svgY) {
        this.startCoords = startCoords;
        this.svgX = svgX;
        this.svgY = svgY;
    }

    public String drawBand() {
        String res = "";
        ld.setIsDotted(true);
        res += ld.LineDrawer(startCoords + 15, svgX - 15, startCoords + 15, svgY - 15);
        res += ld.LineDrawer(startCoords + 15, svgX - 15, svgY - 15, startCoords + 15);
        return res;
    }
}
