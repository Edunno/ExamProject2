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
public class FogSQLException extends Exception {

    public FogSQLException(String msg, Exception ex) {
        
        super(msg);
    }
    
}
