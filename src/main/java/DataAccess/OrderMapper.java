package DataAccess;

import FunctionLayer.partslist.*;
import FunctionLayer.FogExceptions.FogDataException;
import FunctionLayer.DTO.Order;
import FunctionLayer.DTO.Orderline;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The purpose of OrderMapper is to be able to put and pull Order data from the
 * database
 *
 * @author DECK-CS - Kasper & Kim P. Pedersen
 */
public class OrderMapper {

    /**
     * This method creates an order and adds it to the database
     *
     * @param order the order to store
     * @param length of carport in meters
     * @param width of carport in meters
     * @param hasShed boolean true if it has
     * @param slope degrees as int
     * @throws FogDataException exception
     */
    public static int createOrder(Order order, double length, double width, boolean hasShed, int slope) throws FogDataException {
        int oID = 0;
        MaterialMapper mm = new MaterialMapper();
        try {
            Connection con = Connector.connection();
            String SQL1 = "INSERT INTO `Order` (uID, tPrice) VALUES (?, ?)";
            String SQL2 = "INSERT INTO Orderline (Order_oID, Products_pID, Qty, lPrice) VALUES (?, ?, ?, ?)";
            PreparedStatement ps1 = con.prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, order.getuID());
            ps1.setDouble(2, order.gettPrice());
            ps1.executeUpdate();
            ResultSet ids1 = ps1.getGeneratedKeys();
            ids1.next();
            oID = ids1.getInt(1);
            order.setoID(oID);
            storeCarport(oID, length, width, hasShed, slope);

            for (Wood w : order.getPl().getWoodList()) {
                PreparedStatement ps2 = con.prepareStatement(SQL2);
                ps2.setInt(1, order.getoID());
                ps2.setInt(2, w.getId());
                ps2.setInt(3, w.getQty());
                ps2.setDouble(4, w.getQty() * w.getPrice());
                ps2.executeUpdate();
                mm.removeStock(w.getPartNumber(), w.getQty());
            }
            for (Material m : order.getPl().getMatList()) {
                PreparedStatement ps2 = con.prepareStatement(SQL2);
                ps2.setInt(1, order.getoID());
                ps2.setInt(2, m.getId());
                ps2.setInt(3, m.getQty());
                ps2.setDouble(4, m.getQty() * m.getPrice());
                ps2.executeUpdate();
                mm.removeStock(m.getPartNumber(), m.getQty());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
        return oID;
    }

    /**
     * This method returns all orders made by one User by ID
     *
     * @param uID the user id
     * @return ArrayList of Order oById
     * @throws FogDataException exception
     */
    
    
    public static ArrayList<Order> getOrdersbyUID(int uID) throws FogDataException {
        ArrayList<Order> oById = new ArrayList();
        try {
            Connection con = Connector.connection();

            //Statement 1
            String SQL = "SELECT oID, ueID, tPrice, DispatchDate FROM `Order` "
                    + "WHERE uID=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, uID);
            ResultSet rs = ps.executeQuery();
            ArrayList<Orderline> aol = new ArrayList<>();
            while (rs.next()) {
                Date dDate = rs.getDate("DispatchDate");
                int oID = rs.getInt("oID");
                int ueID = rs.getInt("ueID");
                double tPrice = rs.getDouble("tPrice");
                Order o = new Order(dDate, oID, uID, ueID, tPrice, aol);
                //Statement 2
                String SQL2 = "SELECT Products_pID, Qty, lprice FROM Orderline "
                        + "WHERE Order_oID=?";
                PreparedStatement ps2 = con.prepareStatement(SQL2);
                ps2.setInt(1, oID);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int pID = rs2.getInt("Products_pID");
                    double lPrice = rs2.getDouble("lPrice");
                    int Qty = rs2.getInt("Qty");
                    Orderline ol = new Orderline(pID, Qty, lPrice);
                    aol.add(ol);

                }
                o.setAol(aol);
                o.setCp(getCarport(oID));
                oById.add(o);
            }
            return oById;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * This method returns an order from the database by orderID.
     *
     * @param oID order ID as int
     * @return an Order object
     * @throws FogDataException exception
     */
    public static Order getOrderbyoID(int oID) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT uID, ueID, tPrice, DispatchDate FROM FogDB.Order "
                    + "WHERE oID=?";

            String SQL2 = "SELECT Products_pID, Qty, lprice FROM Orderline "
                    + "WHERE Order_oID=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, oID);

            ResultSet rs = ps.executeQuery();
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ps2.setInt(1, oID);
            while (rs.next()) {
                Date dDate = rs.getDate("DispatchDate");
                int uID = rs.getInt("uID");
                int ueID = rs.getInt("ueID");
                double tPrice = rs.getDouble("tPrice");
                ArrayList<Orderline> aol = new ArrayList();
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int pID = rs2.getInt("Products_pID");
                    int qty = rs2.getInt("Qty");
                    int lprice = rs2.getInt("lprice");
                    Orderline o = new Orderline(pID, qty, lprice);
                    aol.add(o);
                }
                Order o = new Order(dDate, oID, uID, ueID, tPrice, aol);
                o.setCp(getCarport(oID));
                return o;

            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This method marks an order as dispatched given the Order ID
     *
     * @param oID the order id
     * @throws FogDataException exception
     */
    public static void markAsDispatch(int oID) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE FogDB.Order set DispatchDate = current_timestamp() WHERE oID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, oID);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

    /**
     * This method returns all orders not dispatched
     *
     * @return ArrayList of Order objects oNotDisp
     * @throws FogDataException exception
     */
    
    public static ArrayList<Order> allOrdersNotDispatched() throws FogDataException {
        ArrayList<Order> oById = new ArrayList();
        try {
            Connection con = Connector.connection();

            //Statement 1
            String SQL = "SELECT oID, uID, ueID, tPrice, DispatchDate FROM `Order` "
                    + "WHERE DispatchDate IS NULL";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            ArrayList<Orderline> aol = new ArrayList<>();
            while (rs.next()) {
                int oID = rs.getInt("oID");
                int ueID = rs.getInt("ueID");
                int uID = rs.getInt("uID");
                double tPrice = rs.getDouble("tPrice");
                Order o = new Order(null, oID, uID, ueID, tPrice, aol);
                //Statement 2
                String SQL2 = "SELECT Products_pID, Qty, lprice FROM Orderline "
                        + "WHERE Order_oID=?";
                PreparedStatement ps2 = con.prepareStatement(SQL2);
                ps2.setInt(1, oID);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int pID = rs2.getInt("Products_pID");
                    double lPrice = rs2.getDouble("lPrice");
                    int Qty = rs2.getInt("Qty");
                    Orderline ol = new Orderline(pID, Qty, lPrice);
                    aol.add(ol);

                }
                o.setAol(aol);
                o.setCp(getCarport(oID));
                oById.add(o);
            }
            return oById;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }

    }
    
    /**
     * This method returns all orders in the DB
     * 
     * @return an ArrayList of Order objects containing all orders
     * @throws FogDataException exception
     */

    public static ArrayList<Order> getAllOrders() throws FogDataException {
        ArrayList<Order> oById = new ArrayList();
        try {
            Connection con = Connector.connection();

            //Statement 1
            String SQL = "SELECT oID, uID, ueID, tPrice, DispatchDate FROM `Order` ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            ArrayList<Orderline> aol = new ArrayList<>();
            while (rs.next()) {
                int oID = rs.getInt("oID");
                int ueID = rs.getInt("ueID");
                int uID = rs.getInt("uID");
                double tPrice = rs.getDouble("tPrice");
                Date DispatchDate = rs.getDate("DispatchDate");
                Order o = new Order(DispatchDate, oID, uID, ueID, tPrice, aol);
                //Statement 2
                String SQL2 = "SELECT Products_pID, Qty, lprice FROM Orderline "
                        + "WHERE Order_oID=?";
                PreparedStatement ps2 = con.prepareStatement(SQL2);
                ps2.setInt(1, oID);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int pID = rs2.getInt("Products_pID");
                    double lPrice = rs2.getDouble("lPrice");
                    int Qty = rs2.getInt("Qty");
                    Orderline ol = new Orderline(pID, Qty, lPrice);
                    aol.add(ol);

                }
                o.setAol(aol);
                o.setCp(getCarport(oID));
                oById.add(o);
            }
            return oById;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }
    
    /**
     * This method stores a Carport object to the DB
     * 
     * 
     * @param oID the order ID attached to the Carport
     * @param length length of the carport in meters
     * @param width  width of the carport in meters
     * @param hasShed set to true if the carport has a shed
     * @param slope degrees of the slope, set to 0 if the roof is flat
     * @throws FogDataException exception
     */

    public static void storeCarport(int oID, double length, double width, boolean hasShed, int slope) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `Carports` (oID, cLength, cWidth, hasShed, cSlope) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps1 = con.prepareStatement(SQL);
            ps1.setInt(1, oID);
            ps1.setDouble(2, length);
            ps1.setDouble(3, width);
            ps1.setBoolean(4, hasShed);
            ps1.setInt(5, slope);
            ps1.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }
    
    /**
     * This method returns a Carport given the order ID
     * 
     * @param oID the order
     * @return Carport object
     * @throws FogDataException exception
     */

    public static Carport getCarport(int oID) throws FogDataException {
        Carport cp = null;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT cLength, cWidth, cSlope, hasShed FROM FogDB.Carports WHERE oID = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, oID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double cLength = rs.getDouble("cLength");
                double cWidth = rs.getDouble("cWidth");
                int cSlope = rs.getInt("cSlope");
                boolean hasShed = rs.getBoolean("hasShed");
                cp = new Carport(cLength, cWidth, cSlope, hasShed);
            }
            return cp;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Updates an order in the DB given an Order object and carport dimensions.
     * 
     * @param order the updated order you want to store
     * @param length length of the carport
     * @param width width of the carport
     * @param hasShed set to true if the carport has a shed
     * @param slope the amount of degrees slope on the roof. set to 0 if flat roof
     * @throws FogDataException exception
     */

    public static void updateOrder(Order order, double length, double width, boolean hasShed, int slope) throws FogDataException {
        try {
            Connection con = Connector.connection();
            String SQL1 = "UPDATE `Order` SET tPrice=? WHERE oID=?";
            String SQL2 = "REPLACE INTO Orderline (Order_oID, Products_pID, Qty, lPrice) VALUES (?, ?, ?, ?)";
            String SQL3 = "UPDATE Carports SET hasShed=TRUE WHERE oID=?";
            PreparedStatement ps1 = con.prepareStatement(SQL1);
            ps1.setDouble(1, order.gettPrice());
            ps1.setInt(2, order.getoID());
            ps1.executeUpdate();
            for (Wood w : order.getPl().getWoodList()) {
                PreparedStatement ps2 = con.prepareStatement(SQL2);
                ps2.setInt(1, order.getoID());
                ps2.setInt(2, w.getId());
                ps2.setInt(3, w.getQty());
                ps2.setDouble(4, w.getQty() * w.getPrice());
                ps2.executeUpdate();
            }
            for (Material m : order.getPl().getMatList()) {
                PreparedStatement ps2 = con.prepareStatement(SQL2);
                ps2.setInt(1, order.getoID());
                ps2.setInt(2, m.getId());
                ps2.setInt(3, m.getQty());
                ps2.setDouble(4, m.getQty() * m.getPrice());
                ps2.executeUpdate();
            }
            PreparedStatement ps3 = con.prepareStatement(SQL3);
            ps3.setInt(1, order.getoID());
            ps3.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogDataException(ex.getMessage(), ex);
        }
    }

}
