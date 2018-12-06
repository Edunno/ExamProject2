/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogExceptions.FogException;
import FunctionLayer.LogicFacade;
import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.FogExceptions.FogSQLException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import FunctionLayer.partslist.Partslist;

/**
 *
 * @author Dan
 */
@WebServlet(name = "Calculate", urlPatterns = {"/Calculate"})
public class Calculate extends Command {

    double numOfShedLogs;
    double mOfWall;
    double mOfWallSupport;
    int slope;
    String addShed;

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException, FogSQLException, FogException {
        response.setContentType("text/html;charset=UTF-8");
        double length = Double.parseDouble(request.getParameter("length"));
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        double width = Double.parseDouble(request.getParameter("width"));
        if (width <= 0) {
            throw new IllegalArgumentException();
        }
        String sroof = request.getParameter("sroof");
        String shed = request.getParameter("shed");
        addShed = request.getParameter("addShed");

        boolean specialRoof = false;
        if (sroof.equals("true")) {
            specialRoof = true;
        }
        boolean hasShed = false;
        if (shed.equals("true")) {
            hasShed = true;
        }

        LogicFacade lf = new LogicFacade();
        int numberOfLogs = lf.calculateLogs(length, width);
        double lenghtOfBand = lf.calculateBands(length, width);
        int numberOfRafters = lf.calculateRafters(length, width, specialRoof);
        int numOfStrops = lf.calculateStrops(length, width);

        if (hasShed) {
            ArrayList<Double> shedInfo = lf.getShedInfo(length, width);
            numOfShedLogs = shedInfo.get(0);
            mOfWall = shedInfo.get(1);
            mOfWallSupport = shedInfo.get(2);
        }
        Partslist pl;
        if (specialRoof) {
            slope = Integer.parseInt(request.getParameter("slope"));
            ArrayList<Double> roofInfo = lf.getRoofInfo(length, width, slope);

            pl = lf.createPartslist(length, width, specialRoof, hasShed, numberOfLogs, numberOfRafters,
                    roofInfo.get(1), numOfStrops, roofInfo.get(2), lenghtOfBand, roofInfo.get(3), (int) numOfShedLogs,
                    mOfWall, mOfWallSupport);
            request.setAttribute("pl", pl);
        } else {
            pl = lf.createPartslist(length, width, specialRoof, hasShed, numberOfLogs, numberOfRafters,
                    width, numOfStrops, lf.calculateRoof(length, width), lenghtOfBand, 0.0, (int) numOfShedLogs,
                    mOfWall, mOfWallSupport);
            request.setAttribute("pl", pl);
        }
        if (specialRoof) {
            String topCarportHtml = lf.drawSpecialCarport(length, width, slope, hasShed);
            String sideCarportHtml = lf.drawSideCarport(length, width, slope, specialRoof, hasShed);
            String frontCarportHtml = lf.drawFrontCarport(length, width, slope, specialRoof, hasShed);
            request.setAttribute("topCarportHTML", topCarportHtml);
            request.setAttribute("sideCarportHTML", sideCarportHtml);
            request.setAttribute("frontCarportHTML", frontCarportHtml);
        } else {
            String topCarportHtml = lf.drawBasicCarport(length, width, hasShed);
            String sideCarportHtml = lf.drawSideCarport(length, width, slope, specialRoof, hasShed);
            String frontCarportHtml = lf.drawFrontCarport(length, width, slope, specialRoof, hasShed);
            request.setAttribute("topCarportHTML", topCarportHtml);
            request.setAttribute("sideCarportHTML", sideCarportHtml);
            request.setAttribute("frontCarportHTML", frontCarportHtml);
        }
        User u = (User) request.getSession().getAttribute("user");
        Order o = new Order(null, 0, u.getId(), 6, pl.getTotalPrice(), null);
        o.setPl(pl);

        if (addShed != null && addShed.equals("yes")) {
            int oID = Integer.parseInt(request.getParameter("oid"));
            int uID = Integer.parseInt(request.getParameter("uid"));
            o = new Order(null, oID, uID, 6, pl.getTotalPrice(), null);
            o.setPl(pl);
            lf.updateOrder(o, length, width, hasShed, slope);

        }
        if (addShed == null) {
            try {
                lf.storeOrder(o, length, width, hasShed, slope);
                ArrayList<Order> ol = lf.getOrdersByUID(u.getId());
                request.getSession().setAttribute("orderList", ol);
            } catch (FogSQLException ex) {
                System.out.println("Error couldn't save your order");
                Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (addShed == null) {
                try {
                    lf.storeOrder(o, length, width, hasShed, slope);
                    ArrayList<Order> ol = lf.getOrdersByUID(u.getId());
                    request.getSession().setAttribute("orderList", ol);
                } catch (FogSQLException ex) {
                    System.out.println("Error couldn't save your order");
                    Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return "vieworderpage";
        }
