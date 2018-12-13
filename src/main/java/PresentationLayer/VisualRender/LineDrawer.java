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
public class LineDrawer {

    private int lineX1, lineX2, lineY1, lineY2;
    private boolean isDotted = false;
    private String stroke = "red";

    /**The primary tool for drawing lines in the SVG. Takes an start and end x value, and a start and end y value. Can be changed to be dotted and to be red or black.
     *
     * @param lineX1
     * @param lineX2
     * @param lineY1
     * @param lineY2
     * @return A String to be inserted into a SVG line.
     */
    public String LineDrawer(int lineX1, int lineX2, int lineY1, int lineY2) {
        this.lineX1 = lineX1;
        this.lineX2 = lineX2;
        this.lineY1 = lineY1;
        this.lineY2 = lineY2;
        return drawer();    //As there is a return value in the constructor, the constructor has to be called as an uninitialized object first, if you want to change if it is dotted or it's color.
    }

    /**Sets whether the line is dotted or not.
     *
     * @param isDotted
     */
    public void setIsDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    /**When called, changes the color of the line to black.
     *
     */
    public void setStrokeBlack() {
        this.stroke = "black";
    }
    

    private String drawer() {
        if (isDotted) {
            return "<line x1=\"" + lineX1 + "\" y1=\"" + lineY1 + "\" x2=\"" + lineX2 + "\" y2=\"" + lineY2 + "\" stroke=\""+stroke+"\" stroke-width=\"3\" stroke-dasharray=\"5, 5\"/>";
        } else {
            return "<line x1=\"" + lineX1 + "\" y1=\"" + lineY1 + "\" x2=\"" + lineX2 + "\" y2=\"" + lineY2 + "\" stroke=\""+stroke+"\" stroke-width=\"3\"/>";
        }
    }
}
