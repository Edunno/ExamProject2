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

    private int shedSizeX, shedSizeY, startX, startY, logDim;
    private String res = "";
    private RectangleDrawer rd = new RectangleDrawer();

    public ShedDrawer( int startX, int startY,int shedSizeX, int shedSizeY, int logDim) {
        this.shedSizeX = shedSizeX;
        this.shedSizeY = shedSizeY;
        this.startX = startX;
        this.startY = startY;
        this.logDim = logDim;
        
        rd.setStroke(5);
    }
    public String mainDrawer(){
        res += rd.RectangleDrawer(startX, startY, shedSizeY, shedSizeX);

        return res;
    }


}
