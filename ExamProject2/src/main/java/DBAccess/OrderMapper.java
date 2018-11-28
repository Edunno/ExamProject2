package DBAccess;

import FogExceptions.FogException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import partslist.*;

/**
 * The purpose of OrderMapper is to be able to put and pull data from the
 * database
 *
 * @author Kasper & Kim P. Pedersen
 */
public class OrderMapper {

    public static void main(String[] args) throws Exception {
        OrderMapper om = new OrderMapper();
        UserMapper um = new UserMapper();
        User u = UserMapper.login("jens@somewhere.com", "Jensen");
//        User u = UserMapper.login("Ken@somewhere.com", "Kensen");

        // ######## Test: createOrder ########
        Partslist pl = new Partslist();
        pl.getWoodList().add(new Wood(101,"Brædt trykimprægneret",150,400,25,200,10));
        pl.getMatList().add(new Material(201,"Plastmo bundskruer 200stk",200,2));
        pl.getMatList().add(new Material(202,"Hulbånd 1x20mm 20m",400,20));
        Order o = new Order(u.getId(), pl.getTotalPrice(), pl);
        createOrder(o);
        System.out.println("Test af createOrder er gennenført");
//        // ######## Test: markAsDispatch ########
//        markAsDispatch(21);
//        Order ol = getOrderbyoID(8); // retrieves one order by orderID. 
//        System.out.println("id: " + ol.getId() + " order: " + ol.toString;);
//        // ######## Test: getOrderCustomerNotDispatch ########
//        ArrayList<Order> gocnd = getOrderCustomerNotDispatch(u);
//        System.out.println(gocnd.size());
//        for (Order order : gocnd) {
//            System.out.println(order.toString());
//        }
//        // ######## Test: getOrderbyID ########
//        ArrayList<Order> on = getOrderbyID(u);
//        System.out.println("Ordre for brugeren: " + on.size());
//        for (Order order : on) {
//        String dDate = dispatchDate(order.getoID());
//            System.out.println(order.allOrdersByIDToString());
//            System.out.println(dDate);
//        }
        // ######## Test: getOrderbyoID ########
//        int i = 21; //Order ID selected
//        Order order = getOrderbyoID(i);
//        String dDate = dispatchDate(i);
//        System.out.println(order.toString());
//        System.out.println(dDate);

//        // ######## Test: allOrdersNotDispatched ########
//        ArrayList<Order> on = allOrdersNotDispatched();
//        for (Order order : on) {
//            System.out.println(order.toString());
//        }
    }

    /**
     * This method creates an order and adds it to the database
     *
     * @param order
     * @throws FogException
     * @throws Exception
     */
    public static void createOrder(Order order) throws FogException, Exception {
        try {
            Connection con = Connector.connection();
            String SQL1 = "INSERT INTO `Order` (uID, tPrice) VALUES (?, ?)";
            String SQL2 = "INSERT INTO Orderline (Order_oID, Products_pID, Qty) VALUES (?, ?, ?)";
            PreparedStatement ps1 = con.prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, order.getuID());
            ps1.setDouble(2, order.gettPrice());
            ps1.executeUpdate();
            ResultSet ids1 = ps1.getGeneratedKeys();
            ids1.next();
            int oID = ids1.getInt(1);
            order.setoID(oID);

            PreparedStatement ps2 = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            for (Wood w : order.getPl().getWoodList()) {
                ps2.setInt(1, order.getoID());
                ps2.setInt(2, w.getId());
                ps2.setInt(3, w.getQty());
                ps2.executeUpdate();
                System.out.println("gemt noget wood");
            }
            for ( Material m : order.getPl().getMatList()) {
                ps2.setInt(1, order.getoID());
                ps2.setInt(2, m.getId());
                ps2.setInt(3, m.getQty());
                ps2.executeUpdate();
            }
            ResultSet ids2 = ps2.getGeneratedKeys();
            ids2.next();
            System.out.println("ordre lagt i DB");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("kan ikke gemme ordre til DB");
            throw new Exception(ex.getMessage());
        }
    }

    /**
//
//    /**
//     * This method returns all orders made by one customer by ID
//     *
//     * @param u
//     * @return ArrayList<Order> oById
//     * @throws FogException
//     */
//    public static ArrayList<Order> getOrderbyID(User u) throws FogException {
//        ArrayList<Order> oById = new ArrayList();
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT dDate, BrickPattern, Length, Width, Height, oID, Fours, Twos, Ones FROM Orders "
//                    + "WHERE id=?";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setInt(1, u.getId());
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String pattern = rs.getString("BrickPattern");
//                int length = rs.getInt("Length");
//                int width = rs.getInt("Width");
//                int heigth = rs.getInt("Height");
//                int oID = rs.getInt("oID");
//                int fours = rs.getInt("Fours");
//                int twos = rs.getInt("Twos");
//                int ones = rs.getInt("Ones");
//                StykListe sl = new StykListe(fours, twos, ones);
//                Order o = new Order(sl, u, length, width, heigth);
//                o.setoID(oID);
//                o.setPattern(pattern);
//                oById.add(o);
//            }
//            return oById;
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new FogException(ex.getMessage());
//        }
//    }
//     * This method returns an order from the database by orderID.
//     *
//     * @param oID
//     * @return an order
//     * @throws LEGOException
//     */
//    public static Order getOrderbyoID(int oID) throws LEGOException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT BrickPattern, Length, Width, Height, id, Fours, Twos, Ones FROM Orders "
//                    + "WHERE oID=?";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setInt(1, oID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String pattern = rs.getString("BrickPattern");
//                int length = rs.getInt("Length");
//                int width = rs.getInt("Width");
//                int heigth = rs.getInt("Height");
//                int id = rs.getInt("id");
//                int fours = rs.getInt("Fours");
//                int twos = rs.getInt("Twos");
//                int ones = rs.getInt("Ones");
//                StykListe sl = new StykListe(fours, twos, ones);
//                Order o = new Order(oID, sl, id, length, width, heigth, pattern);
//                return o;
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new FogException(ex.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * This method returns all orders from a customer which is not dispatched
//     *
//     * @return
//     * @throws FogException
//     */
//    public static ArrayList<Order> getOrderCustomerNotDispatch(User u) throws FogException {
//        ArrayList<Order> oNotDispCustomer = new ArrayList();
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT * FROM LegoHouseDB.Orders WHERE dDate IS NULL AND id=?;";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setInt(1, u.getId());
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String pattern = rs.getString("BrickPattern");
//                int length = rs.getInt("Length");
//                int width = rs.getInt("Width");
//                int heigth = rs.getInt("Height");
//                int oID = rs.getInt("oID");
//                int fours = rs.getInt("Fours");
//                int twos = rs.getInt("Twos");
//                int ones = rs.getInt("Ones");
//                int id = rs.getInt("id");
//                StykListe sl = new StykListe(fours, twos, ones);
//                Order oto = new Order(oID, sl, id, length, width, heigth, pattern);
//                oNotDispCustomer.add(oto);
//            }
//            return oNotDispCustomer;
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new FogException(ex.getMessage());
//        }
//    }
//
//    /**
//     * This method marks an order as dispatched
//     *
//     * @param oID
//     * @throws LEGOException
//     */
//    public static void markAsDispatch(int oID) throws LEGOException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "UPDATE Orders set dDate = current_timestamp() WHERE oID = ?;";
//            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, oID);
//            ps.executeUpdate();
//            ResultSet ids = ps.getGeneratedKeys();
//            ids.next();
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new LEGOException(ex.getMessage());
//        }
//    }
//
//    /**
//     * This method returns all orders not dispatched
//     *
//     * @returnArrayList<Order> oNotDisp
//     * @throws LEGOException
//     */
//    public static ArrayList<Order> allOrdersNotDispatched() throws FogException {
//        ArrayList<Order> oNotDisp = new ArrayList();
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT * FROM LegoHouseDB.Orders WHERE dDate IS NULL;";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Date dDate = rs.getDate("dDate");
//                String pattern = rs.getString("BrickPattern");
//                int length = rs.getInt("Length");
//                int width = rs.getInt("Width");
//                int heigth = rs.getInt("Height");
//                int oID = rs.getInt("oID");
//                int fours = rs.getInt("Fours");
//                int twos = rs.getInt("Twos");
//                int ones = rs.getInt("Ones");
//                int id = rs.getInt("id");
//                StykListe sl = new StykListe(fours, twos, ones);
//                Order oto = new Order(oID, sl, id, length, width, heigth, pattern);
//                oNotDisp.add(oto);
//            }
//            return oNotDisp;
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new FogException(ex.getMessage());
//        }
//    }
//
//    /**
//     * This method returns the dispatch date as a String
//     *
//     * @param oID
//     * @return String dispatchDate
//     * @throws LEGOException
//     */
//    public static String dispatchDate(int oID) throws FogException {
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
//            throw new FogException(ex.getMessage());
//        }
//        return null;
//    }
//
}
