/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DataAccess.Connector;
import DataAccess.UserMapper;
import DataAccess.OrderMapper;
import FunctionLayer.FogExceptions.FogCreateUserException;
import FunctionLayer.FogExceptions.FogException;
import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.LogicFacade;
import FunctionLayer.DTO.Order;
import FunctionLayer.DTO.Orderline;
import FunctionLayer.DTO.User;
import FunctionLayer.partslist.Carport;
import FunctionLayer.partslist.Partslist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Esben
 */
public class OrderMapperIT {

    private static Order o1;
    private static User u1F;

    public OrderMapperIT() {
    }

    @BeforeClass
    public static void setUpClass() throws FogCreateUserException, ClassNotFoundException, FogLoginException, FogException {
        User u1 = new User("arnegimbel@outlook.com", "ellebelle", "customer");
        UserMapper.createUser(u1);
        u1F = UserMapper.login(u1.getEmail(), u1.getPassword());

        ////////////////
        int slope = 25;
        double length = 4.5;
        double width = 6.5;
        boolean hasShed = true;
        boolean specialRoof = true;
        ////////////////

        Carport cp = new Carport(length, width, slope, hasShed);
        LogicFacade lf = new LogicFacade();
        int numberOfLogs = lf.calculateLogs(length, width);
        double lenghtOfBand = lf.calculateBands(length, width);
        int numberOfRafters = lf.calculateRafters(length, width, specialRoof);
        int numOfStrops = lf.calculateStrops(length, width);

        ArrayList<Double> shedInfo = lf.getShedInfo(length, width);
        double numOfShedLogs = shedInfo.get(0);
        double mOfWall = shedInfo.get(1);
        double mOfWallSupport = shedInfo.get(2);

        ArrayList<Double> roofInfo = lf.getRoofInfo(length, width, slope);

        Partslist pl = lf.createPartslist(length, width, specialRoof, hasShed, numberOfLogs, numberOfRafters, 0,
                roofInfo.get(1), numOfStrops, roofInfo.get(2), lenghtOfBand, roofInfo.get(3), (int) numOfShedLogs,
                mOfWall, mOfWallSupport);

        ArrayList<Orderline> Aol = new ArrayList();
        Orderline oL = new Orderline(210, 5, 6, 10.4);
        Aol.add(oL);
        o1 = new Order(null, 210, u1F.getId(), 0, 22.32, Aol);
        o1.setPl(pl);
        double olength = 22.0;
        double owidth = 22.0;
        boolean ohasShed = false;
        int oslope = 10;
        OrderMapper.createOrder(o1, olength, owidth, ohasShed, oslope);
    }

    @AfterClass
    public static void tearDownClass() throws FogCreateUserException, ClassNotFoundException {
                try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM Carports WHERE oID = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o1.getoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            throw new FogCreateUserException(ex.getMessage(), ex);
        }
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM Orderline WHERE Order_oID = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o1.getoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            throw new FogCreateUserException(ex.getMessage(), ex);
        }
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `Order` WHERE uID = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, u1F.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            throw new FogCreateUserException(ex.getMessage(), ex);
        }
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM User WHERE Email = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u1F.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            throw new FogCreateUserException(ex.getMessage(), ex);
        }

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOrdersbyID method, of class OrderMapper.
     */
    @Test
    public void testGetOrdersbyID() throws Exception {
        System.out.println("getOrdersbyID");
        int uID = u1F.getId();
        ArrayList<Order> result = OrderMapper.getOrdersbyUID(uID);
        assertEquals(false, result.isEmpty());
    }

    /**
     * Test of getOrderbyoID method, of class OrderMapper.
     */
    @Test
    public void testGetOrderbyoID() throws Exception {
        System.out.println("getOrderbyoID");
        int oID = o1.getoID();
        Order expResult = o1;
        Order result = OrderMapper.getOrderbyoID(oID);
        assertEquals(expResult.getuID(), result.getuID());
    }

    /**
     * Test of allOrdersNotDispatched method, of class OrderMapper.
     */
    @Test
    public void testAllOrdersNotDispatched() throws Exception {
        System.out.println("allOrdersNotDispatched");
        ArrayList<Order> result = OrderMapper.allOrdersNotDispatched();
        assertEquals(false, result.isEmpty());
    }

    /**
     * Test of markAsDispatch method, of class OrderMapper.
     */
    @Test
    public void testMarkAsDispatch() throws Exception {
        System.out.println("markAsDispatch");
        OrderMapper.markAsDispatch(o1.getoID());
        Date eR = Date.from(Instant.now());
        
        Order result = OrderMapper.getOrderbyoID(o1.getoID());
        int timeRel = eR.compareTo(result.getdDate());
        assertEquals(1, timeRel);
    }
    
    /**
     * Test of getAllOrders method, of class OrderMapper.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");
        ArrayList<Order> result = OrderMapper.getAllOrders();
        assertEquals(false, result.isEmpty());
        assertEquals(true,(result.get(0).getoID()>0));
    }
    
    /**
     * Test of getCarport method, of class OrderMapper.
     */
    @Test
    public void testGetCarport() throws Exception {
        System.out.println("getCarport");
        int oID = o1.getoID();
        double expResult = 22.0;
        Carport result = OrderMapper.getCarport(oID);
        assertEquals(expResult, result.getcLength(),0.01);
    }
    
}
