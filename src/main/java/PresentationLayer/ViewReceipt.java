/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogExceptions.FogLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author caspe
 */
public class ViewReceipt extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {

        String command = request.getParameter("command");
        if (command.equals("receipt")) {
            return "receipt";
        }
        return null;
    }

}
