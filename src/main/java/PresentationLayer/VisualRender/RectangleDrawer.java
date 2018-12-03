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
public class RectangleDrawer {

    private int startX;
    private int startY;
    private int lengthX;
    private int lengthY;
    private int stroke = 1;
    private double fillOp = 0;
    private String fillCol = "white";
    
    public String RectangleDrawer(int startX, int startY, int lengthY, int lengthX){
        this.startX = startX;
        this.startY = startY;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        return drawer();
    }
    public void setStroke(int stroke){
        this.stroke = stroke;
    }

    public void setFillOp(double fill) {
        this.fillOp = fill;
    }

    public void setFillCol(String fillCol) {
        this.fillCol = fillCol;
    }
    
    
    private String drawer(){
        return "<RECT x=\""+startX+"\" y=\""+startY+"\" height=\""+lengthY+"\" width=\""+lengthX+"\" stroke=\"black\" stroke-width=\""+stroke+"\" fill=\""+fillCol+"\" fill-opacity=\""+fillOp+"\"/>";
    }
}
