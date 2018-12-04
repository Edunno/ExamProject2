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

    public String LineDrawer(int lineX1, int lineX2, int lineY1, int lineY2) {
        this.lineX1 = lineX1;
        this.lineX2 = lineX2;
        this.lineY1 = lineY1;
        this.lineY2 = lineY2;
        return drawer();
    }

    public void setIsDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

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