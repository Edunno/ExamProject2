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

    /**
     * Primary tool for drawing standard rectangles in SVG.
     *
     * @param startX upper left x value
     * @param startY upper left y value
     * @param lengthY distance it moves down from start y value
     * @param lengthX distance it moves to the right from start x value
     * @return String for use in SVG.
     */
    public String RectangleDrawer(int startX, int startY, int lengthY, int lengthX) {
        this.startX = startX;
        this.startY = startY;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        return drawer();
    }

    /**
     * Sets the stroke width of the rectangle outline.
     *
     * @param stroke default 1.
     */
    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    /**
     * Sets the opacity of the ractangles fill. 1 is solid and 0 is fully
     * transparanet.
     *
     * @param fill double between 1 and 0.
     */
    public void setFillOp(double fill) {
        this.fillOp = fill;
    }

    /**
     * Sets the color of the fill.
     *
     * @param fillCol String. e.g. "black" or "blue".
     */
    public void setFillCol(String fillCol) {
        this.fillCol = fillCol;
    }

    private String drawer() {
        return "<RECT x=\"" + startX + "\" y=\"" + startY + "\" height=\"" + lengthY + "\" width=\"" + lengthX + "\" stroke=\"black\" stroke-width=\"" + stroke + "\" fill=\"" + fillCol + "\" fill-opacity=\"" + fillOp + "\"/>";
    }
}
