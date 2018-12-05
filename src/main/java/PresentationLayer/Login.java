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
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = LogicFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        LogicFacade lf = new LogicFacade();
        session.setAttribute("role", user.getRole());
        ArrayList<Order> ol = new ArrayList();
        if (user.getRole().equals("employee")) {
            try {
                ol = lf.getOrdersNotDispatched();
                session.setAttribute("orderList", ol);
            } catch (FogSQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "employeepage";
        } else {
            try {
                ol = lf.getOrdersByUID(user.getId());
                session.setAttribute("orderList", ol);
            } catch (FogSQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "customerloginpage";
    }

}
