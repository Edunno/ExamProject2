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

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {
        response.setContentType("text/html;charset=UTF-8");
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        String sroof = request.getParameter("sroof");
        String shed = request.getParameter("shed");

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
        request.setAttribute("numberOfLogs", numberOfLogs);
        request.setAttribute("numberOfRafters", numberOfRafters);
        if (hasShed) {
            ArrayList<Double> shedInfo = lf.getShedInfo(length, width);
            numOfShedLogs = shedInfo.get(0);
            mOfWall = shedInfo.get(1);
            mOfWallSupport = shedInfo.get(2);
        }
        Partslist pl;
        if (specialRoof) {
            int slope = Integer.parseInt(request.getParameter("slope"));
            ArrayList<Double> roofInfo = lf.getRoofInfo(length, width, slope);
            request.setAttribute("heightOfRoof", roofInfo.get(0));
            request.setAttribute("rafterLenght", roofInfo.get(1));
            request.setAttribute("areaOfRoof", roofInfo.get(2));
            request.setAttribute("areaOfGable", roofInfo.get(3));
            pl = lf.createPartslist(length, width, specialRoof, hasShed, numberOfLogs, numberOfRafters,
                    roofInfo.get(1), numOfStrops, roofInfo.get(2), lenghtOfBand, roofInfo.get(3), (int) numOfShedLogs,
                    mOfWall, mOfWallSupport);
            request.setAttribute("pl", pl);
        } else {
            request.setAttribute("areaOfRoof", lf.calculateRoof(length, width));
            request.setAttribute("lenghtOfBand", lenghtOfBand);
            pl = lf.createPartslist(length, width, specialRoof, hasShed, numberOfLogs, numberOfRafters,
                    width, numOfStrops, lf.calculateRoof(length, width), lenghtOfBand, 0.0, (int) numOfShedLogs,
                    mOfWall, mOfWallSupport);
            request.setAttribute("pl", pl);
        }
        String carportHtml = lf.drawCarport(length, width, hasShed);
        request.setAttribute("carportHTML", carportHtml);
        User u = (User) request.getSession().getAttribute("user");
        Order o = new Order(null, 0, u.getId() , 6, pl.getTotalPrice(), null);
        o.setPl(pl);
        try {
            lf.storeOrder(o);
            ArrayList<Order> ol = lf.getOrdersByUID(u.getId());
            request.getSession().setAttribute("orderList", ol);
        } catch (Exception ex) {
            System.out.println("fejl i ordre til Database");
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "vieworderpage";
    }

}
