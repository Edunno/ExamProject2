/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    }

    public ArrayList<Material> getAllMaterials() {
        int id = 0;
        String name = "";
        int price = 0;
        int qty = 0;
        ArrayList<Material> MatList = new ArrayList();
        try {
            Connection con = Connector.connection();
            Statement st = con.createStatement();
            String query
                    = "SELECT *"
                    + "FROM `Products`"
                    + "WHERE `pCategory` = 'Material';";

            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                id = res.getInt("pID");
                name = res.getString("pName");
                price = res.getInt("pPrice");
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
            Connection con = Connector.connection();
            Statement st = con.createStatement();
            String query
                    = "SELECT *"
                    + "FROM `Products`"
                    + "WHERE `pCategory` = 'Wood';";

            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                id = res.getInt("pID");
                name = res.getString("pName");
                price = res.getInt("pPrice");
                height = res.getInt("pHeight");
                width = res.getInt("pWidth");
                length = res.getInt("pLength");
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

    public void addWoodToDB(Wood w) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Products (pID, pName, pPrice, pLength, pWidth, pHeight, pCategory) "
                    + "VALUES (?, ?, ?, ?,? , ?, 'Wood')";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, w.getId());
            ps.setString(2, w.getName());
            ps.setDouble(3, w.getPrice());
            ps.setDouble(4, w.getLength());
            ps.setDouble(5, w.getWidth());
            ps.setDouble(6, w.getHeight());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public void addMatToDB(Material m) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Products (pID, pName, pPrice, pCategory) "
                    + "VALUES (?, ?, ?, 'Material')";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getId());
            ps.setString(2, m.getName());
            ps.setDouble(3, m.getPrice());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

}
