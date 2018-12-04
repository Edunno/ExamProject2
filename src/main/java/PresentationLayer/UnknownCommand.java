package PresentationLayer;

import FunctionLayer.FogExceptions.FogException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The purpose of UnknownCommand is to...
 *
 * @author kasper
 */
public class UnknownCommand extends Command {

    private Exception ex;

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        String msg = "Unknown command. Contact IT";
        try {
            throw new FogException(msg, ex);
        } catch (FogException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
