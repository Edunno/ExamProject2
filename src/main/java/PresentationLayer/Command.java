package PresentationLayer;

import FunctionLayer.FogExceptions.FogLoginException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {
    
    private static HashMap<String, Command> commands;
    
    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("calculate", new Calculate());
        commands.put("addProduct", new AddProduct());
        commands.put("neworder", new CustomerPick());
        commands.put("history", new CustomerPick());
        commands.put("order", new CustomerPick());
    }
    
    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }
    
    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws FogLoginException;
    
}