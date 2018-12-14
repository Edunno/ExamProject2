/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.VisualRender;

/**
 *
 * @author Esben
 */
public class PolygonDrawer {
    int x1,x2,x3,x4,y1,y2,y3,y4;
    String fillCol = "white";
    double fillOp = 1;

    /**The main tool for drawing polygons. Is only geared to draw four sided polygons, e.g. trapezes and parallelograms. 
     *
     * @param x1 first x value
     * @param y1 first y value
     * @param x2 second x value
     * @param y2 second y value
     * @param x3 third x value
     * @param y3 third y value
     * @param x4 fourth x value
     * @param y4 fourth y value
     * @return A String to be inserted into a SVG line.
     */
    public String PolygonDrawer(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        return drawer(); //As there is a return value in the constructor, the constructor has to be called as an uninitialized object first, if you want to change if it is dotted or it's color.
    }
    
    private String drawer(){
        return "<POLYGON points=\""+x1+","+y1+" "+x2+","+y2+","+x3+","+y3+" "+x4+","+y4+"\" stroke=\"black\" stroke-width=\"1\" fill=\""+fillCol+"\" fill-opacity=\""+fillOp+"\"/>";
    }

    /**Sets the opacity of the polygons fill.
     *
     * @param fillOp 1 is solid and 0 is fully transparent.
     */
    public void setOp(double fillOp){
        this.fillOp = fillOp;
    }

    /**Sets the color of the polygons fill.
     *
     * @param fillCol String. e.g. "black" or "blue".
     */
    public void setFillCol(String fillCol){
        this.fillCol = fillCol;
    }
}
