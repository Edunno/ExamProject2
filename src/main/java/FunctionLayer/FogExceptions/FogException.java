/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.FogExceptions;

/**
 * THIS IS INTENDED TO BE THE DEFAULT EXCEPTION!
 * NEEDS TO BE DONE
 * @author KimPPedersen
 */
public class FogException extends Exception {

    private Exception ex;

    /**
     * Constructs an instance of <code>FogException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     * @param ex
     */
    public FogException(String msg, Exception ex) {
        super(msg);
        this.ex = ex;
    }
}
