/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.FogExceptions.FogSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import FunctionLayer.partslist.Material;
import FunctionLayer.partslist.Wood;

/**
 *
 * @author Dan
 */
public class MaterialMapper {

    public static void main(String[] args) {
        MaterialMapper mm = new MaterialMapper();

        System.out.println(mm.getAllProductNames(2));

    }

    public ArrayList<Material> getAllMaterials() {
        int id = 0;
        String name = "";
        int price = 0;
        int qty = 0;
        int partNumber = 0;
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
                partNumber = res.getInt("partNumber");
                Material m = new Material(id, name, price, qty, partNumber);
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
        int partNumber = 0;
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
                partNumber = res.getInt("partNumber");
                Wood w = new Wood(id, name, price, height, width, length, qty, partNumber);
                WoodList.add(w);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return WoodList;
    }

    public String getAllProductNames(int pID) {
        String name = "";
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT pName FROM Products WHERE pID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, pID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("pName");
            }
            return name;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Material getMaterial(ArrayList<Material> listOfMats, int partNumber) {
        for (Material m : listOfMats) {
            if (m.getPartNumber() == partNumber) {
                return m;
            }
        }
        return null;
    }

    public Wood getWood(ArrayList<Wood> listOfWood, int partNumber) {
        for (Wood w : listOfWood) {
            if (w.getPartNumber() == partNumber) {
                return w;
            }
        }
        return null;
    }

    public void addWoodToDB(Wood w) throws FogLoginException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Products (partNumber, pName, pPrice, pLength, pWidth, pHeight, pCategory) "
                    + "VALUES (?, ?, ?, ?,? , ?, 'Wood')";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, w.getPartNumber());
            ps.setString(2, w.getName());
            ps.setDouble(3, w.getPrice());
            ps.setDouble(4, w.getLength());
            ps.setDouble(5, w.getWidth());
            ps.setDouble(6, w.getHeight());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogLoginException(ex.getMessage(), ex);
        }
    }

    public void addMatToDB(Material m) throws FogLoginException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Products (partNumber, pName, pPrice, pCategory) "
                    + "VALUES (?, ?, ?, 'Material')";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getPartNumber());
            ps.setString(2, m.getName());
            ps.setDouble(3, m.getPrice());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogLoginException(ex.getMessage(), ex);
        }
    }

}
