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
    
    public String RectangleDrawer(int startX, int startY, int lengthX, int lengthY){
        this.startX = startX;
        this.startY = startY;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        return drawer();
    }
    
    
    public String drawer(){
        int a = 20;
        return "<RECT x=\""+startX+"\" y=\""+startY+"\" height=\""+lengthX+"\" width=\""+lengthY+"\" stroke=\"black\" stroke-width=\"1\"/>";
    }
}
