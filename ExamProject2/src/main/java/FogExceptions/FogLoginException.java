/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FogExceptions;

/**
 * THIS IS INTENDED TO BE THE LOGIN EXCEPTION!
 * NEEDS TO BE DONE
 * @author KimPPedersen
 */
public class FogLoginException extends Exception {

    /**
     * Creates a new instance of <code>FogException</code> without detail
     * message.
     */
    public FogLoginException() {
    }

    /**
     * Constructs an instance of <code>FogException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FogLoginException(String msg) {
        super(msg);
    }
}
