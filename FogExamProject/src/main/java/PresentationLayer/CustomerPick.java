package PresentationLayer;

import FunctionLayer.LogicFacade;
import FogExceptions.FogLoginException;
import FunctionLayer.User;
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

        String command = request.getParameter("command");

        if (command.equals("neworder")) {

            return "customerpage";
        } else {
            return "orderhistory";
        }
    }
}
