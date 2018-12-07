/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.FogExceptions.FogDataException;
import FunctionLayer.Order;
import FunctionLayer.User;
import FunctionLayer.partslist.Carport;
import java.util.ArrayList;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogDataException {
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
            slope = Integer.parseInt(request.getParameter("slope"));
            specialRoof = true;
        }
        boolean hasShed = false;
        if (shed.equals("true")) {
            hasShed = true;
        }
        Carport cp = new Carport(length, width, slope, hasShed);
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
        Order o = null;
        User u = (User) request.getSession().getAttribute("user");
        if (u.getRole().equals("customer")) {
            o = new Order(null, 0, u.getId(), 6, pl.getTotalPrice(), null);
            o.setPl(pl);
            o.setCp(cp);
        }

        if (addShed != null && addShed.equals("yes")) {
            int oID = Integer.parseInt(request.getParameter("oid"));
            int uID = Integer.parseInt(request.getParameter("uid"));
            o = new Order(null, oID, uID, 6, pl.getTotalPrice(), null);
            o.setPl(pl);
            o.setCp(cp);
        }

        request.getSession().setAttribute("currentOrder", o);

        return "vieworderpage";
    }
}
