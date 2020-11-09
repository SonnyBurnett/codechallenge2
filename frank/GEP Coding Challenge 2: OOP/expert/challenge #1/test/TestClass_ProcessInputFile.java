package test;

import data.ProcessInputFile;
import data.ProductException;
import domain.Products;


/**
 * Test class for testing the ProcessInputFile data package class.
 */
public class TestClass_ProcessInputFile implements ITestClass {


    private final String INFILE         =   System.getProperty( "user.dir" ) + "\\src\\data\\001-experts-inputs.csv";


    /**
     * Uses the toString methods of the classes Product and Products to present the content in the input file.
     */
    @Override
    public void testClass()  {

        ProcessInputFile pif            =   new ProcessInputFile( this.INFILE );
        try {
            pif.processInFile();
            Products prods = pif.getProducts();
            System.out.println( "Products header: " + pif.getProductsHeader() + "\n" );
            System.out.println( "Products:\n" + prods.toString() );
        }   //  End-of try-clause
        catch( ProductException pe ) {
            System.out.println( pe.getMessage() );
        }   //  End-of catch-clause

    }   //  End-of method testClass


    public static void main( String[] args ) throws ProductException {

        TestClass_ProcessOutputFile tc  =   new TestClass_ProcessOutputFile();
        tc.testClass();

    }   //  End-of method main


}   //  End-of class TestClass_ProcessInputFile