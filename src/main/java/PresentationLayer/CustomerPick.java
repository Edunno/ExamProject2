package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.FogExceptions.FogSQLException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The purpose of Login is to...
 *
 * @author kasper
 */
public class CustomerPick extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogSQLException {

        LogicFacade lf = new LogicFacade();

        ArrayList<Order> ol = new ArrayList();
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals("customer")) {
            try {
                ol = lf.getOrdersByUID(user.getId());
            } catch (FogSQLException ex) {
                Logger.getLogger(CustomerPick.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("allOrders") != null) {
            if (request.getParameter("allOrders").equals("yes")) {
                ol = lf.getAllOrders();
            }
            if (request.getParameter("allOrders").equals("no")) {
                ol = lf.getOrdersNotDispatched();
            }
        }
        request.getSession().setAttribute("orderList", ol);

        String command = request.getParameter("command");
        if (request.getParameter("command").equals("order") && user.getRole().equals("customer")) {
            Order o = (Order) request.getSession().getAttribute("currentOrder");
            try {
                int oID = lf.storeOrder(o, o.getCp().getcLength(), o.getCp().getcWidth(), o.getCp().isHasShed(), o.getCp().getcSlope());
                o = lf.getOrderByOID(oID);
                request.getSession().setAttribute("currentOrder", o);
                ol = lf.getOrdersByUID(user.getId());
                request.getSession().setAttribute("orderList", ol);
            } catch (FogSQLException ex) {
                System.out.println("Error couldn't save your order");
                Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "viewsingleorder";
        }

        if (request.getParameter("command").equals("order") && user.getRole().equals("employee")) {
            Order o = (Order) request.getSession().getAttribute("currentOrder");
            lf.updateOrder(o, o.getCp().getcLength(), o.getCp().getcWidth(), o.getCp().isHasShed(), o.getCp().getcSlope());
            o = lf.getOrderByOID(o.getoID());
            request.getSession().setAttribute("currentOrder", o);
            return "viewsingleorder";
        }
        if (command.equals("neworder")) {
            return "customerpage";
        }
        if (command.equals("history")) {

        }
        return "orderhistory";
    }

}
