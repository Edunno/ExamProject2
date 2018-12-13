/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.FogExceptions.FogException;
import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.ErrorLogger.FogErrorLogger;
import static FunctionLayer.ErrorLogger.FogErrorLogger.PRODUCTION;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kasper
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws FunctionLayer.FogExceptions.FogException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        try {
            Command action = Command.from(request);
            String view = action.execute(request, response);
            request.getRequestDispatcher(view + ".jsp").forward(request, response);
        } catch (FogLoginException ex) {
            FogErrorLogger.getLogger(PRODUCTION, true).log(Level.SEVERE, null, ex);
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (IllegalArgumentException ex) {
            FogErrorLogger.getLogger(PRODUCTION, true).log(Level.SEVERE, null, ex);
            request.setAttribute("Wrong input", ex.getMessage());
            request.getRequestDispatcher("customerorderpage.jsp").forward(request, response);
        } catch (ArithmeticException ex) {
            FogErrorLogger.getLogger(PRODUCTION, true).log(Level.SEVERE, null, ex);
            request.setAttribute("Your input can't be negative or zero", ex.getMessage());
            request.getRequestDispatcher("customerorderpage.jsp").forward(request, response);
        } catch (FogException ex) {
            FogErrorLogger.getLogger(PRODUCTION, true).log(Level.SEVERE, null, ex);
            request.setAttribute("Your input can't be negative or zero", ex.getMessage());
            request.getRequestDispatcher("customerorderpage.jsp").forward(request, response);
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
