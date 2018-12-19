package FunctionLayer.FogExceptions;

/**
 * The purpose of FogLoginException is to...
 *
 * @author DECK-CS, Kasper
 */
public class FogLoginException extends FogException {

    private Exception ex; 
    
    /**
     * This is the exception for when a user tries to login 
     * @param msg The message sends to the super class
     * @param ex The exception object passed on to the parent exception class
     */
    public FogLoginException(String msg, Exception ex) {
        super(msg, ex);
    }
}
