/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

/**
 *
 * @author Esben
 */
public class ShedCalculator {

private double sizeX, sizeY;
private double spacing = 2.5;
private int logs = 0;
private double mOfWall = 0;
private double mOfWallSupport = 0;


    public ShedCalculator(double sizeX, double sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.spacing = spacing/2;
        calcWall();
        calcExtraLogs();
    }

    private void calcWall() {
        double wallX = sizeX*1.125;
        double wallY = sizeY*1.125;
        mOfWall += wallX/0.10*2 + wallY/0.10*2;
        mOfWallSupport += sizeX*4+sizeY*4+2; //Two extra meters for door support
    }

    private void calcExtraLogs() {
        logs += (int) (sizeX/spacing+1+sizeY/spacing+1)-2;
    }

    public int getLogs() {
        return logs;
    }

    public double getmOfWall() {
        return mOfWall;
    }

    public double getmOfWallSupport() {
        return mOfWallSupport;
    }

    public double getSpacing() {
        return spacing;
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }
    
    
    
}
