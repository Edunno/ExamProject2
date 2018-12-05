package FunctionLayer.FogExceptions;

/**
 * The purpose of FogLoginException is to...
 *
 * @author DECK-CS, Kasper
 */
public class FogLoginException extends FogException {

    private Exception ex;

    public FogLoginException(String msg, Exception ex) {
        super(msg, ex);
    }
}
