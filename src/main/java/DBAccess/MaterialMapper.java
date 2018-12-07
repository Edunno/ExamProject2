/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FogExceptions.FogSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import FunctionLayer.partslist.Material;
import FunctionLayer.partslist.Partslist;
import FunctionLayer.partslist.Wood;

/**
 *
 * @author Dan
 */
public class MaterialMapper {

    public static void main(String[] args) throws FogSQLException {
        MaterialMapper mm = new MaterialMapper();
        System.out.println(mm.getStock(201));
        mm.addStock(1, 100);

    }

    public ArrayList<Material> getAllMaterials() {
        int id = 0;
        String name = "";
        int price = 0;
        int qty = 0;
        int partNumber = 0;
        int stock;
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
                stock = res.getInt("stockQty");
                Material m = new Material(id, name, price, qty, partNumber);
                m.setStock(stock);
                MatList.add(m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return MatList;
    }

    public ArrayList<Wood> getAllWood() {
        int id;
        int partNumber;
        String name;
        double price;
        double height;
        double width;
        double length;
        int stock;

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
                stock = res.getInt("stockQty");
                Wood w = new Wood(id, name, price, height, width, length, 0, partNumber);
                w.setStock(stock);
                WoodList.add(w);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return WoodList;
    }

    public String getAllProductNames(int pID) throws FogSQLException {
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
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
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

    public int addWoodToDB(Wood w) throws FogSQLException {
        int pID;
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
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            pID = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
        return pID;
    }

    public int addMatToDB(Material m) throws FogSQLException {
        int pID;
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Products (partNumber, pName, pPrice, pCategory) "
                    + "VALUES (?, ?, ?, 'Material')";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getPartNumber());
            ps.setString(2, m.getName());
            ps.setDouble(3, m.getPrice());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            pID = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
        return pID;
    }

    public boolean isMatInStock(Material m, int qtyNeeded) throws FogSQLException {
        int stock = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT stockQty FROM Products WHERE partNumber =?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getPartNumber());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt("stockQty");
            }
            if (stock > qtyNeeded) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    public boolean isWoodInStock(Wood w, int qtyNeeded) throws FogSQLException {
        int stock = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT stockQty FROM Products WHERE partNumber =?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, w.getPartNumber());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt("stockQty");
            }
            if (stock > qtyNeeded) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    public int getStock(int pID) throws FogSQLException {
        int stock = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT stockQty FROM Products WHERE pID =?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, pID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt("stockQty");
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
        return stock;
    }

    public Partslist checkPartslistForStock(Partslist pl) throws FogSQLException {
        Partslist plOutOfStock = new Partslist();
        for (Wood w : pl.getWoodList()) {
            if (!isWoodInStock(w, w.getQty())) {
                plOutOfStock.getWoodList().add(w);
            }
        }
        for (Material m : pl.getMatList()) {
            if (!isMatInStock(m, m.getQty())) {
                plOutOfStock.getMatList().add(m);
            }
        }

        return plOutOfStock;
    }

    public void removeStock(int pID, int qty) throws FogSQLException {
        int newStock = getStock(pID) - qty;
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE Products SET stockQty = ? WHERE pID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newStock);
            ps.setInt(2, pID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    public void addStock(int pID, int qty) throws FogSQLException {
        int newStock = getStock(pID) + qty;
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE Products SET stockQty = ? WHERE pID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newStock);
            ps.setInt(2, pID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    public void removeMaterialFromDB(int pID) throws FogSQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE from Products WHERE pID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, pID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }
}
