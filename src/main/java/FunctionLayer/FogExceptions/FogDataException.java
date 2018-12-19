/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.FogExceptions;

/**
 *
 * @author KimPPedersen
 */
public class FogDataException extends FogException {

    private Exception ex;
    
     /**Handles exceptions when interacting with the database.
     *
     * @param msg the String message passed on to the parent exception class
     * @param ex The exception object passed on to the parent exception class
     */
    public FogDataException(String msg, Exception ex) {
        super(msg, ex);
    }
    
}
