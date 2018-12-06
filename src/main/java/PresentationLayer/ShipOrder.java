/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.FogExceptions.FogSQLException;
import FunctionLayer.LogicFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dan
 */
public class ShipOrder extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {
        int oID = Integer.parseInt(request.getParameter("oid"));
        LogicFacade lf = new LogicFacade();
        try {
            lf.markAsDispatch(oID);
        } catch (FogSQLException ex) {
            Logger.getLogger(ShipOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewsingleorder";
    }

}