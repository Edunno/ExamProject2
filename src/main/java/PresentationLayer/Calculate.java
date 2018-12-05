/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {
        response.setContentType("text/html;charset=UTF-8");
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        String sroof = request.getParameter("sroof");
        String shed = request.getParameter("shed");
        addShed = request.getParameter("addShed");

        boolean specialRoof = false;
        if (sroof.equals("true") || Integer.parseInt(sroof) > 0) {
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
        String carportHtml = lf.drawCarport(length, width, hasShed);
        request.setAttribute("carportHTML", carportHtml);
        User u = (User) request.getSession().getAttribute("user");
        Order o;
        if (addShed.equals("yes")) {
            
            int oID = Integer.parseInt(request.getParameter("oid"));
            int uID = Integer.parseInt(request.getParameter("uid"));
            o = new Order(null, oID, uID, 6, pl.getTotalPrice(), null);

        } else {
            o = new Order(null, 0, u.getId(), 6, pl.getTotalPrice(), null);
        }
        o.setPl(pl);
        try {
            lf.storeOrder(o, length, width, hasShed, slope);
            ArrayList<Order> ol = lf.getOrdersByUID(u.getId());
            request.getSession().setAttribute("orderList", ol);
        } catch (Exception ex) {
            System.out.println("fejl i ordre til Database");
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "vieworderpage";
    }

}
