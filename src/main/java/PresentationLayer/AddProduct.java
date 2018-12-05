/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dan
 */
public class AddProduct extends Command {

    public AddProduct() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogLoginException {
        int partNumber = Integer.parseInt(request.getParameter("partNumber"));
        String name = request.getParameter("pName");
        double pPrice = Double.parseDouble(request.getParameter("pPrice"));
        double pLength = Double.parseDouble(request.getParameter("pLength"));
        double pHeight = Double.parseDouble(request.getParameter("pHeight"));
        double pWidth = Double.parseDouble(request.getParameter("pWidth"));
        
        LogicFacade lf = new LogicFacade();
        
        
        return "employeepage";
    }

}
