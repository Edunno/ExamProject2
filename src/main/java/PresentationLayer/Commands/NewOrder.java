/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.FogExceptions.FogException;
import FunctionLayer.LogicFacade;
import FunctionLayer.DTO.Order;
import FunctionLayer.DTO.User;
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
@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class NewOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Order> ol = new ArrayList();
        Order o = (Order) request.getSession().getAttribute("currentOrder");
        LogicFacade lf = new LogicFacade();
        int oID = lf.storeOrder(o, o.getCp().getcLength(), o.getCp().getcWidth(), o.getCp().isHasShed(), o.getCp().getcSlope());
        o = lf.getOrderByOID(oID);
        request.getSession().setAttribute("currentOrder", o);
        ol = lf.getOrdersByUID(user.getId());
        request.getSession().setAttribute("orderList", ol);
        return "viewsingleorder";
    }

}
