/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.util.ArrayList;
import partslist.Material;
import partslist.Wood;

/**
 *
 * @author Dan
 */
public class DataMapper {

    public Material getMaterial(ArrayList<Material> listOfMats, int id) {
        for (Material m : listOfMats) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public Wood getWood(ArrayList<Wood> listOfWood, int id) {
        for (Wood w : listOfWood) {
            if (w.getId() == id) {
                return w;
            }
        }
        return null;
    }

    /**
     * Replace with MySQL statements
     * 
     * @return 
     */
    public ArrayList<Material> createDummyMats() {
        ArrayList<Material> mats = new ArrayList();
        mats.add(new Material(201, "Plastmo bundskruer 200stk", 200, 1));
        mats.add(new Material(202, "Hulbånd 1x20mm 20m", 400, 1));
        mats.add(new Material(203, "Universal 190mm højre", 50, 1));
        mats.add(new Material(204, "Universal 190mm venstre", 50, 1));
        mats.add(new Material(205, "4,5x60mm skruer 200stk", 200, 1));
        mats.add(new Material(206, "4,0x50mm beslagskruer 200 stk", 500, 1));
        mats.add(new Material(207, "Bræddebolt 10x120 mm.", 5, 1));
        mats.add(new Material(208, "Firkantskive 40x40x11mm", 15, 1));
        mats.add(new Material(209, "4,5x70mm Skruer 400 stk", 100, 1));
        mats.add(new Material(210, "4,5x50mm Skruer 400 stk", 100, 1));
        mats.add(new Material(211, "Stalddørsgreb 50x75", 200, 1));
        mats.add(new Material(212, "T hængsel 390 mm", 100, 1));
        mats.add(new Material(213, "B & C sort tagsten", 10, 1));
        return mats;
    }
    
    /**
     * Replace with MySQL statements
     * 
     * 
     * 
     * @return 
     */

    public ArrayList<Wood> createDummyWood() {
        ArrayList<Wood> wood = new ArrayList();
        wood.add(new Wood(101, "Brædt trykimprægneret", 150, 2.5, 20, 400, 1));
        wood.add(new Wood(102, "Brædt trykimprægneret", 200, 2.5, 20, 600, 1));
        wood.add(new Wood(103, "Brædt trykimprægneret", 100, 2.5, 12.5, 400, 1));
        wood.add(new Wood(104, "Brædt trykimprægneret", 125, 2.5, 12.5, 600, 1));
        wood.add(new Wood(105, "Lægte ubehandlet", 50, 3.8, 7.3, 400, 1));
        wood.add(new Wood(106, "Reglar ubehandlet", 50, 4.5, 9.5, 300, 1));
        wood.add(new Wood(107, "Spærtræ ubehandlet", 200, 4.5, 19.5, 400, 1));
        wood.add(new Wood(108, "Spærtræ ubehandlet", 300, 4.5, 19.5, 600, 1));
        wood.add(new Wood(109, "Stolpe trykimprægneret", 100, 9.7, 9.7, 300, 1));
        wood.add(new Wood(110, "Brædt trykimprægneret", 50, 1.9, 10, 500, 1));
        wood.add(new Wood(111, "Plastmo Ecolite blåtonet", 200, 1, 100, 600, 1));

        return wood;
    }

}
