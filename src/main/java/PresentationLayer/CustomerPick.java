package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.ArrayList;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {

        LogicFacade lf = new LogicFacade();

        ArrayList<Order> ob = new ArrayList();

        request.setAttribute("uorders", ob);

        String command = request.getParameter("command");

        if (command.equals("neworder")) {

            return "customerpage";
        } else {
            return "orderhistory";
        }
    }
}
