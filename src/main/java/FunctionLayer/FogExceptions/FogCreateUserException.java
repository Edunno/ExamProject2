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
public class FogCreateUserException extends FogException {

    /**Handles exceptions when a costumer tries to create a user.
     *
     * @param msg the String message passed on to the parent exception class
     * @param ex The exception object passed on to the parent exception class
     */
    public FogCreateUserException(String msg, Exception ex) {
        super(msg, ex);
    }

    
}
