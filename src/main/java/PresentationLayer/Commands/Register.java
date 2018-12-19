package PresentationLayer.Commands;

import FunctionLayer.FogExceptions.FogCreateUserException;
import FunctionLayer.LogicFacade;
import FunctionLayer.DTO.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This Command is used for registering new users.
 * 
 * @author Kasper
 */

public class Register extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (password1.equals(password2)) {
            User user;
            try {
                user = LogicFacade.createUser(email, password1);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                return "index";
            } catch (FogCreateUserException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
