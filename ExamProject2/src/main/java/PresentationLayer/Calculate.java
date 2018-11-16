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

/**
 *
 * @author Dan
 */
@WebServlet(name = "Calculate", urlPatterns = {"/Calculate"})
public class Calculate extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        response.setContentType("text/html;charset=UTF-8");
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int slope = Integer.parseInt(request.getParameter("slope"));
        String sroof = request.getParameter("sroof");
        boolean specialRoof = false;
        if (sroof.equals("true")) {
            specialRoof = true;
        }
        LogicFacade lf = new LogicFacade();
        int numberOfLogs = lf.calculateLogs(length, width);
        double lenghtOfBand = lf.calculateBands(length, width);
        int numberOfRafters = lf.calculateRafters(length, width, specialRoof);
        request.setAttribute("numberOfLogs", numberOfLogs);
        request.setAttribute("lenghtOfBand", lenghtOfBand);
        request.setAttribute("numberOfRafters", numberOfRafters);

        if (specialRoof) {
            ArrayList<Double> roofInfo = lf.getRoofInfo(length, width, slope);
            request.setAttribute("heightOfRoof", roofInfo.get(0));
            request.setAttribute("rafterLenght", roofInfo.get(1));
            request.setAttribute("areaOfRoof", roofInfo.get(2));
            request.setAttribute("areaOfGable", roofInfo.get(3));
        } else {
            request.setAttribute("areaOfRoof", lf.calculateRoof(length, width));
        }
        return "vieworderpage";
    }

}
