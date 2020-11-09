package test;

import data.ProductException;


public class TestClass_ProductException implements ITestClass {


    /**
     *
     * Parameterless constructor.
     * @throws ProductException Thrown to test constructor
     */
    private void throwProductException() throws ProductException {
        throw new ProductException();
    }   //  End-of parameterless constructor


    /**
     *
     * Parameterized constructor.
     * @param msg Contains string message, to be retrieved by the getMessage method.
     * @throws ProductException Thrown to test class constructor.
     */
    private void throwProductException( String msg ) throws ProductException {
            throw new ProductException( msg );
    }   //  End-of parameterized constructor


    /**
     * Test class for testing the ProductException data package class.
     */
    @Override
    public void testClass() {
        try {
            this.throwProductException( "This is a test for throwing and handling ProductException" );
        }   //  End-of try-clause
        catch( ProductException pe ) {
            System.out.println( "\n"+ pe.getMessage() );
            System.out.println( "Stacktrace:");
            pe.printStackTrace();
        }   //  End-of catch-clause
    }   //  End-of method testClass


    public static void main( String[] args ) {

        TestClass_ProductException tc       =   new TestClass_ProductException();
        tc.testClass();

    }   //  End-of method main


}   //  End-of class TestClass_ProductException