/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogExceptions.FogException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dan
 */
@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class NewOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        Order o = (Order) request.getSession().getAttribute("newOrder");
        LogicFacade lf = new LogicFacade();
        lf.storeOrder(o, o.getCp().getcLength(), o.getCp().getcWidth(), o.getCp().isHasShed(), o.getCp().getcSlope());
        return "viewsingleorder";
    }

}
