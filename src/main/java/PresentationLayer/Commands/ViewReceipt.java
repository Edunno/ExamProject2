/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.Commands;

import FunctionLayer.FogExceptions.FogException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *This command is called when viewing the receipt
 * @author caspe
 */
public class ViewReceipt extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {

        return "receipt";
    }

}
