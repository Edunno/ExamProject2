/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import partslist.Material;
import partslist.Wood;

/**
 *
 * @author Dan
 */
public class DataMapper {

    public static void main(String[] args) {
        DataMapper m = new DataMapper();
        System.out.println(m.getAllMaterials());
    }

    public ArrayList<Material> getAllMaterials() {
        int id = 0;
        String name = "";
        int price = 0;
        int qty = 0;
        ArrayList<Material> MatList = new ArrayList();
        try {
            Connection c = new Connector().connection();
            Statement st = c.createStatement();
            String query
                    = "SELECT *"
                    + "FROM `FogDB.Products`;";

            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                price = res.getInt("price");
                qty = res.getInt("qty");
                Material m = new Material(id, name, price, qty);
                MatList.add(m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return MatList;
    }

    public ArrayList<Wood> getAllWood() {
        int id = 0;
        String name = "";
        double price = 0;
        double height = 0;
        double width = 0;
        double length = 0;
        int qty = 0;
        ArrayList<Wood> WoodList = new ArrayList();
        try {
            Connection c = new Connector().connection();
            Statement st = c.createStatement();
            String query
                    = "SELECT *"
                    + "FROM `Products`;";

            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                id = res.getInt(id);
                name = res.getString("name");
                price = res.getInt("price");
                height = res.getInt("variant");
                width = res.getInt("width");
                length = res.getInt("length");
                qty = res.getInt("qty");
                Wood w = new Wood(id, name, price, height, width, length, qty);
                WoodList.add(w);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return WoodList;
    }

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

}
