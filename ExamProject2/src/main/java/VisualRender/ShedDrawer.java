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

    private int shedSizeX, shedSizeY, logDim;
    private String res = "";
    private LineDrawer ld = new LineDrawer();

    public ShedDrawer(int shedSizeX, int shedSizeY, int logDim) {
        this.shedSizeX = shedSizeX;
        this.shedSizeY = shedSizeY;
        this.logDim = logDim;
    }
    public String mainDrawer(){
        return res;
    }


}
