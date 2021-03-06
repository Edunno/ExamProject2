/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.FogExceptions.FogDataException;
import FunctionLayer.LogicFacade;
import FunctionLayer.DTO.Order;
import FunctionLayer.partslist.Carport;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *This command is used to mark an order as dispatched as well as getting the new order list.
 * @author Dan
 */
public class ShipOrder extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogDataException {
        int oID = Integer.parseInt(request.getParameter("oid"));
        LogicFacade lf = new LogicFacade();
        try {
            lf.markAsDispatch(oID);
            ArrayList<Order> ol = lf.getOrdersNotDispatched();
            request.getSession().setAttribute("orderList", ol);
            Order o = lf.getOrderByOID(oID);
            Carport cp = lf.getCarport(oID);
            o.setCp(cp);
            request.getSession().setAttribute("currentOrder", o);
        } catch (FogDataException ex) {
            Logger.getLogger(ShipOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewsingleorder";
    }

}
