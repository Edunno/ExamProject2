/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.VisualRender;

import FunctionLayer.calculators.ShedCalculator;

/**
 *
 * @author Esben
 */
public class ShedDrawer {

    private int shedSizeX, shedSizeY, startX, startY, logDim;
    private String res = "";
    private RectangleDrawer rd = new RectangleDrawer();

    /**Takes in the information needed to draw the topdown view of the shed.
     *
     * @param startX Dimension x start of shed.
     * @param startY Dimension y start of shed
     * @param shedSizeX Size in dimension x.
     * @param shedSizeY Size in dimesnion y.
     * @param logDim the dimension of the logs used for supporting the shed walls.
     */
    public ShedDrawer( int startX, int startY,int shedSizeX, int shedSizeY, int logDim) {
        this.shedSizeX = shedSizeX;
        this.shedSizeY = shedSizeY;
        this.startX = startX;
        this.startY = startY;
        this.logDim = logDim;
        
        rd.setStroke(6);
    }

    /**Processes the information from the constructor and returns a String that can be inserted into an SVG String.
     *
     * @return String for instertion into SVG String.
     */
    public String mainDrawer(){
        res += rd.RectangleDrawer(startX+(logDim/2), startY-(logDim/2), shedSizeY+logDim, shedSizeX+(logDim/2));
        ShedCalculator sc = new ShedCalculator((double)(shedSizeX*100),(double)(shedSizeY*100));
        int space = (int)((sc.getSpacing()*100));
        int logsX = shedSizeX/space+1;
        int logsY = shedSizeY/space+1;
        rd.setStroke(1);
        for(int i = 0; i < logsX; i++){
            res +=rd.RectangleDrawer(startX+(i*shedSizeX/logsX)+(logDim/2), startY-(logDim/2), logDim, logDim);
        }
        for(int i = 0; i < logsX;i++){
            res +=rd.RectangleDrawer(startX+(i*shedSizeX/logsX)+(logDim/2), shedSizeY+logDim+3, logDim, logDim); //3 is quite likely a function of the stroke width
        }
        for(int i = 1; i < logsY;i++){
            res += rd.RectangleDrawer(startX+(logDim/2), startY+(i*shedSizeY/logsY)-(logDim/2), logDim, logDim);
        }
        for(int i = 1; i < logsY;i++){
            res += rd.RectangleDrawer(startX+shedSizeX, startY+(i*shedSizeY/logsY)-(logDim/2), logDim, logDim);
        }
        return res;
    }


}
