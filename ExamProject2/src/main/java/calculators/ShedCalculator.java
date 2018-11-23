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
private double spacing;
private int logs = 0;
private double mOfWall = 0;
private double mOfWallSupport = 0;

    public static void main(String[] args) {
        ShedCalculator sc = new ShedCalculator(3.0,2.0,2.5);
        System.out.println(sc.getLogs());
        System.out.println(sc.getmOfWall());
        System.out.println(sc.getmOfWallSupport());
    }

    public ShedCalculator(double sizeX, double sizeY, double spacing) {
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
    
}
