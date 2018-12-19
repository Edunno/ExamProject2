package PresentationLayer.Commands;

import FunctionLayer.FogExceptions.FogException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command is a servlet superclass.
 * New servlets are to be put in the commands hashmap with a string as a key.
 * The key string is stored in the forms on the jsp's and submitted through the FrontController
 * @author Kasper
 */

public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("calculate", new Calculate());
        commands.put("addProduct", new AddProduct());
        commands.put("neworder", new Directory());
        commands.put("history", new Directory());
        commands.put("order", new NewOrder());
        commands.put("vieworder", new ViewOrder());
        commands.put("receipt", new ViewReceipt());
        commands.put("shiporder", new ShipOrder());
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws FogException;
    
}
