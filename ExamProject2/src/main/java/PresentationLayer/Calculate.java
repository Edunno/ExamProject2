/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
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
public class Calculate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int length = Integer.parseInt(request.getParameter("lenght"));
        int width = Integer.parseInt(request.getParameter("width"));
        int slope = Integer.parseInt(request.getParameter("slope"));
        boolean specialRoof = true; // TO DO!!!!!!
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

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
