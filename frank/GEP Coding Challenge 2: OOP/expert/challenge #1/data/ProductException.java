package data;

public class ProductException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message      =   null;


    public ProductException() {
        super();
    }   //  End of constructor

    /**
     *
     * @param message User defined message to be included in the exception object.
     */
    public ProductException( String message ) {
        super( message );
        this.message            =   message;
    }   //  End-of constructor

    // Remove?
    public String getMessage() {
        return this.message;
    }   //  End-of method getMessage


}   //  End-of class
