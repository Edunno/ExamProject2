/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.FogExceptions.FogSQLException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import FunctionLayer.partslist.Carport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dan
 */
public class ViewOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {
        LogicFacade lf = new LogicFacade();
        int oid = Integer.parseInt(request.getParameter("oid"));

        try {
            Order o = lf.getOrderByOID(oid);
            Carport cp = lf.getCarport(oid);
            request.getSession().setAttribute("currentOrder", o);
            request.setAttribute("carport", cp);
            
        } catch (FogSQLException ex) {
            Logger.getLogger(ViewOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "viewsingleorder";
    }

}
