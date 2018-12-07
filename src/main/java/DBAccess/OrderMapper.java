package DBAccess;

import FunctionLayer.partslist.*;
import FunctionLayer.FogExceptions.FogSQLException;
import FunctionLayer.Order;
import FunctionLayer.Orderline;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The purpose of OrderMapper is to be able to put and pull data from the
 * database
 *
 * @author DECK-CS - Kasper & Kim P. Pedersen
 */
public class OrderMapper {

    public static void main(String[] args) throws Exception {
        OrderMapper om = new OrderMapper();
        UserMapper um = new UserMapper();
        User u = UserMapper.login("dan", "123");
//        User u = UserMapper.login("kim", "123");
//        User u = UserMapper.login("Ken@somewhere.com", "Kensen");

        // ######## Test: createOrder ########
//        Partslist pl = new Partslist();
//        pl.getWoodList().add(new Wood(101, "Brædt trykimprægneret", 150, 400, 25, 200, 10));
//        pl.getMatList().add(new Material(201, "Plastmo bundskruer 200stk", 200, 2));
//        pl.getMatList().add(new Material(202, "Hulbånd 1x20mm 20m", 400, 20));
//        Order o = new Order(u.getId(), pl.getTotalPrice(), pl);
//        createOrder(o);
//        System.out.println("Test af createOrder er gennenført");
        // ######## Test: markAsDispatch ########
//        // ######## Test: getOrderCustomerNotDispatch ########
//        ArrayList<Order> gocnd = getOrderCustomerNotDispatch(u);
//        System.out.println(gocnd.size());
//        for (Order order : gocnd) {
//            System.out.println(order.toString());
//        }
//        // ######## Test: getOrdersbyID ########
//        ArrayList<Order> on = getOrdersbyID(5);
//        System.out.println(on.get(0).getoID());
//        System.out.println("Ordre for brugeren: " + on.size());
//        System.out.println(on.get(0).gettPrice());
//        for (Order order : on) {
//        String dDate = dispatchDate(order.getoID());
//            System.out.println(order.allOrdersByIDToString());
//            System.out.println(dDate);
//        }
        // ######## Test: getOrderbyoID ########
//        int i = 1; //Order ID selected
//        Order order = getOrderbyoID(i);
//        
//        System.out.println(order.getAol().get(0).getQty());
//        // ######## Test: allOrdersNotDispatched ########
        ArrayList<Order> on = getAllOrders();
        for (Order order : on) { 
            System.out.println(order.getAol().get(0).getlPrice());
        }
    }

    /**
     * This method creates an order and adds it to the database
     *
     * @param order
     * @throws FogSQLException
     */
    public static int createOrder(Order order, double length, double width, boolean hasShed, int slope) throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }
        return oID;
    }

    /**
     * This method returns all orders made by one customer by ID
     *
     * @param u
     * @return ArrayList<Order> oById
     * @throws FogSQLException
     */
    public static ArrayList<Order> getOrdersbyUID(int uID) throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    /**
     * This method returns an order from the database by orderID.
     *
     * @param oID
     * @return an order
     * @throws FogSQLException
     */
    public static Order getOrderbyoID(int oID) throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This method returns all orders from a customer which is not dispatched
     *
     * @return
     * @throws FogSQLException
     */
    public static ArrayList<Order> getOrderCustomerNotDispatch(int uID) throws FogSQLException {
        ArrayList<Order> oNotDispCustomer = new ArrayList();
        try {
            Connection con = Connector.connection();
            //Statement 1
            String SQL = "SELECT * FROM FogDB.Order WHERE DispatchDate IS NULL AND id=?;";
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
                o.setCp(getCarport(oID));
                oNotDispCustomer.add(o);
            }
            return oNotDispCustomer;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    /**
     * This method marks an order as dispatched
     *
     * @param oID
     * @throws FogSQLException
     */
    public static void markAsDispatch(int oID) throws FogSQLException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE FogDB.Order set DispatchDate = current_timestamp() WHERE oID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, oID);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    /**
     * This method returns all orders not dispatched
     *
     * @returnArrayList<Order> oNotDisp
     * @throws FogSQLException
     */
    public static ArrayList<Order> allOrdersNotDispatched() throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }

    }

    public static ArrayList<Order> getAllOrders() throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    public static void storeCarport(int oID, double length, double width, boolean hasShed, int slope) throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    public static Carport getCarport(int oID) throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

    public static void updateOrder(Order order, double length, double width, boolean hasShed, int slope) throws FogSQLException {
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
            throw new FogSQLException(ex.getMessage(), ex);
        }
    }

//
//    /**
//     * This method returns the dispatch date as a String
//     *
//     * @param oID
//     * @return String dispatchDate
//     * @throws FogSQLException
//     */
//    public static String dispatchDate(int oID) throws FogSQLException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT dDAte FROM Orders "
//                    + "WHERE oID=?";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setInt(1, oID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Date dDate = rs.getDate("dDate");
//                if (dDate == null) {
//                    return "your order is still being processes";
//                }
//                return "your order has been dispatch: " + dDate.toString();
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new FogSQLException(ex.getMessage(), ex);
//        }
//        return null;
//    }
//
}
