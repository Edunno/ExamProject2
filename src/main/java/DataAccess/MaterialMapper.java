/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import FunctionLayer.FogExceptions.FogDataException;
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

    /**
     * This method returns all Materials from the database
     *
     * @return ArrayList Materials
     */
    public static ArrayList<Material> getAllMaterials() {
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
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return MatList;
    }

    /**
     * This method retrieves all Wood from the database
     *
     * @return ArrayList of Wood
     */
    public static ArrayList<Wood> getAllWood() {
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
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return WoodList;
    }

    /**
     * Gets the product name from the DB given the product ID
     *
     * @param pID the product ID
     * @return String name
     * @throws FogDataException
     */
    public static String getProductName(int pID) throws FogDataException {
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
            con.close();
            return name;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * This method adds a wood object to the DB
     *
     * @param w the Wood object to be stored
     * @throws FogDataException exception
     */
    public static void addWoodToDB(Wood w) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Products (partNumber, pName, pPrice, pLength, pWidth, pHeight, pCategory) "
                    + "VALUES (?, ?, ?, ?,? , ?, 'Wood')";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, w.getPartNumber());
            ps.setString(2, w.getName());
            ps.setDouble(3, w.getPrice());
            ps.setDouble(4, w.getLength());
            ps.setDouble(5, w.getWidth());
            ps.setDouble(6, w.getHeight());
            ps.executeUpdate();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * This method adds a Material object to the DB
     *
     *
     * @param m the material object
     * @throws FogDataException exception
     */
    public static void addMatToDB(Material m) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Products (partNumber, pName, pPrice, pCategory) "
                    + "VALUES (?, ?, ?, 'Material')";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, m.getPartNumber());
            ps.setString(2, m.getName());
            ps.setDouble(3, m.getPrice());
            ps.executeUpdate();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }

    }

    /**
     * Checks if there is the required stock in the DB of a material object.
     *
     * @param m the Material object
     * @param qtyNeeded the quantity needed
     * @return true if there is enough in stock
     * @throws FogDataException exception
     */
    public static boolean isMatInStock(Material m, int qtyNeeded) throws FogDataException {
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
            con.close();
            if (stock > qtyNeeded) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * Checks if there is the required stock in the DB of a wood object
     *
     * @param w the Wood object
     * @param qtyNeeded the quantity needed
     * @return true if there is enough in stock
     * @throws FogDataException exception
     */
    public static boolean isWoodInStock(Wood w, int qtyNeeded) throws FogDataException {
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
            con.close();
            if (stock > qtyNeeded) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * This methods get the stock from the DB using the product ID
     *
     * @param pID the product id
     * @return int amount of stock
     * @throws FogDataException exception
     */
    public static int getStock(int pID) throws FogDataException {
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
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
        return stock;
    }

    /**
     * Checks a Partslist object for stock
     *
     * @param pl partslist being checked
     * @return a Partslist of all the products not in stock
     * @throws FogDataException exception
     */
    public static Partslist checkPartslistForStock(Partslist pl) throws FogDataException {
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

    /**
     * Removes stock from the DB using product ID
     *
     * @param pID the product ID
     * @param qty quantity to remove
     * @throws FogDataException exception
     */
    public static void removeStock(int pID, int qty) throws FogDataException {
        int newStock = getStock(pID) - qty;
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE Products SET stockQty = ? WHERE pID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newStock);
            ps.setInt(2, pID);
            ps.executeUpdate();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * Adds stock to a product in the DB using the product ID
     *
     * @param pID the product id
     * @param qty the quantity to add
     * @throws FogDataException exception
     */
    public static void addStock(int pID, int qty) throws FogDataException {
        int newStock = getStock(pID) + qty;
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE Products SET stockQty = ? WHERE pID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newStock);
            ps.setInt(2, pID);
            ps.executeUpdate();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * Removes a product from the DB using the product ID
     *
     * @param pID the product ID
     * @throws FogDataException exception
     */
    public static void removeMaterialFromDB(int pID) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE from Products WHERE pID=?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, pID);
            ps.executeUpdate();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * This method changes the partnumber of a product in the DB given the
     * product ID.
     *
     * @param pID the prodcut ID
     * @param partNumber the partnumber
     * @throws FogDataException exception
     */
    public static void changePartNumber(int pID, int partNumber) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE Products SET partNumber = ? WHERE pID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, partNumber);
            ps.setInt(2, pID);
            ps.executeUpdate();
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }
}
