package PresentationLayer.Commands;

import PresentationLayer.Commands.ViewOrder;
import PresentationLayer.Commands.ShipOrder;
import PresentationLayer.Commands.UnknownCommand;
import PresentationLayer.Commands.CustomerPick;
import PresentationLayer.Commands.Register;
import PresentationLayer.Commands.Login;
import PresentationLayer.Commands.ViewReceipt;
import PresentationLayer.Commands.AddProduct;
import PresentationLayer.Commands.Calculate;
import FunctionLayer.FogExceptions.FogException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("calculate", new Calculate());
        commands.put("addProduct", new AddProduct());
        commands.put("neworder", new CustomerPick());
        commands.put("history", new CustomerPick());
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