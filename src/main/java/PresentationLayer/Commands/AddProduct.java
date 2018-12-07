/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.FogExceptions.FogDataException;
import FunctionLayer.LogicFacade;
import FunctionLayer.partslist.Material;
import FunctionLayer.partslist.Wood;
import java.util.ArrayList;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogDataException {
        LogicFacade lf = new LogicFacade();
        String name = "";
        double pPrice = 0;
        int partNumber = 0;
        if (request.getParameter("partNumber") != null) {
            partNumber = Integer.parseInt(request.getParameter("partNumber"));
            name = request.getParameter("pName");
            pPrice = Double.parseDouble(request.getParameter("pPrice"));
        }
        if (request.getParameter("pLength") != null || request.getParameter("pHeight") != null || request.getParameter("pWidth") != null ) {
            if(!request.getParameter("pLength").isEmpty() || !request.getParameter("pHeight").isEmpty() || !request.getParameter("pWidth").isEmpty()){
            double pLength = Double.parseDouble(request.getParameter("pLength"));
            double pHeight = Double.parseDouble(request.getParameter("pHeight"));
            double pWidth = Double.parseDouble(request.getParameter("pWidth"));
            Wood w = new Wood(0, name, pPrice, pLength, pHeight, pWidth, 0, partNumber);
            lf.addWoodToDB(w);
        } else {
            Material m = new Material(0, name, pPrice, 0, partNumber);
            lf.addMaterialToDB(m);
        }
        }
        if (request.getParameter("remove") != null) {
            int pID = Integer.parseInt(request.getParameter("remove"));
            lf.removeMaterialFromDB(pID);
        }
        
        if (request.getParameter("addStock") != null) {
           int qty = Integer.parseInt(request.getParameter("qty"));
           int pID = Integer.parseInt(request.getParameter("addStock"));
           lf.addStockToDB(pID, qty);
        }
        ArrayList<Wood> woodList = lf.getAllWood();
        ArrayList<Material> matList = lf.getAllMaterials();
        request.getSession().setAttribute("woodList", woodList);
        request.getSession().setAttribute("matList", matList);

        return "employeepage";
    }

}
