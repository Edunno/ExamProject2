/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.FogExceptions.FogException;

/**
 *
 * @author KimPPedersen
 */
public class FogCreateUserException extends FogException {

    public FogCreateUserException(String msg, Exception ex) {
        super(msg, ex);
    }

    
}
