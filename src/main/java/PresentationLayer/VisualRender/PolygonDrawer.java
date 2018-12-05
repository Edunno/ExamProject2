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

    public String PolygonDrawer(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        return drawer();
    }
    
    public String drawer(){
        return "<POLYGON points=\""+x1+","+y1+" "+x2+","+y2+","+x3+","+y3+" "+x4+","+y4+"\" stroke=\"black\" stroke-width=\"1\" fill=\""+fillCol+"\" fill-opacity=\""+fillOp+"\"/>";
    }
    public void setOp(double fillOp){
        this.fillOp = fillOp;
    }
    public void setFillCol(String fillCol){
        this.fillCol = fillCol;
    }
}
