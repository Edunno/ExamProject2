/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import partslist.Partslist;

/**
 *
 * @author Dan
 */
@WebServlet(name = "Calculate", urlPatterns = {"/Calculate"})
public class Calculate extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        response.setContentType("text/html;charset=UTF-8");
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        String sroof = request.getParameter("sroof");
        boolean specialRoof = false;
        if (sroof.equals("true")) {
            specialRoof = true;
        }
        LogicFacade lf = new LogicFacade();
        int numberOfLogs = lf.calculateLogs(length, width);
        double lenghtOfBand = lf.calculateBands(length, width);
        int numberOfRafters = lf.calculateRafters(length, width, specialRoof);
        int numOfStrops = lf.calculateStrops(length, width);
        request.setAttribute("numberOfLogs", numberOfLogs);
        request.setAttribute("numberOfRafters", numberOfRafters);

        if (specialRoof) {
            int slope = Integer.parseInt(request.getParameter("slope"));
            ArrayList<Double> roofInfo = lf.getRoofInfo(length, width, slope);
            request.setAttribute("heightOfRoof", roofInfo.get(0));
            request.setAttribute("rafterLenght", roofInfo.get(1));
            request.setAttribute("areaOfRoof", roofInfo.get(2));
            request.setAttribute("areaOfGable", roofInfo.get(3));
            Partslist pl = lf.createPartslist(length, width, specialRoof, numberOfLogs, numberOfRafters,
                    roofInfo.get(1), numOfStrops, roofInfo.get(2), lenghtOfBand, roofInfo.get(3));
            request.setAttribute("pl", pl);
        } else {
            request.setAttribute("areaOfRoof", lf.calculateRoof(length, width));
            request.setAttribute("lenghtOfBand", lenghtOfBand);
            Partslist pl = lf.createPartslist(length, width, specialRoof, numberOfLogs, numberOfRafters,
                    width, numOfStrops, lf.calculateRoof(length, width), lenghtOfBand, 0.0);
            request.setAttribute("pl", pl);
        }

        return "vieworderpage";
    }

}
