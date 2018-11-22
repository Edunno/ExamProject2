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
public class RectangleDrawer {

    private int startX;
    private int startY;
    private int lengthX;
    private int lengthY;
    private int stroke;
    
    public String RectangleDrawer(int startX, int startY, int lengthY, int lengthX){
        this.startX = startX;
        this.startY = startY;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        stroke = 1;
        return drawer();
    }
    
    private String drawer(){
        return "<RECT x=\""+startX+"\" y=\""+startY+"\" height=\""+lengthY+"\" width=\""+lengthX+"\" stroke=\"black\" stroke-width=\""+stroke+"\" fill=\"white\"/>";
    }
}
