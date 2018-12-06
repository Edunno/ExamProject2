package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.FogExceptions.FogSQLException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of Login is to...
 *
 * @author kasper
 */
public class CustomerPick extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException, FogSQLException {

        LogicFacade lf = new LogicFacade();

        ArrayList<Order> ob = new ArrayList();
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals("customer")) {
            try {
                ob = lf.getOrdersByUID(user.getId());
            } catch (FogSQLException ex) {
                Logger.getLogger(CustomerPick.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ob = lf.getOrdersNotDispatched();
        
        }
        request.setAttribute("orderList", ob);

        String command = request.getParameter("command");

        if (command.equals("neworder")) {

            return "customerpage";
        } else {
            return "orderhistory";
        }
    }
}
