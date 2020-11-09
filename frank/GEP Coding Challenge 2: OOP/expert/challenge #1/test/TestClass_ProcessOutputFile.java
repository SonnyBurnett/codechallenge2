package test;

import data.ProcessOutputFile;
import data.ProductException;
import domain.Product;
import domain.Products;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Test class for testing the ProcessOutputFile data package class.
 */
public class TestClass_ProcessOutputFile implements ITestClass {

    private boolean remove              =   true;   //  Set to false in order to keep file for inspaction
    private File file                   =   null;
    private final String OUTFILE        =   System.getProperty( "user.dir" ) + "\\src\\test\\test-outfile.csv";
    private String header               =   "productId, name,  description, price, category";
    private Products prods              =   new Products();


    public TestClass_ProcessOutputFile() {
        this.file                       =   new File( this.OUTFILE );
    }   //  End-of constructor


    /**
     *
     * @param remove Setter for attribute remove, used in method testFile. Used to
     *               determine if the specified output file should be removed after creation.
     */
    public void setRemove( boolean remove ) {
        this.remove                     =   remove;
    }   //  End-of method setRemove


    /**
     *
     * @param wait When true, user will be prompted to press ENTER before the output file will be removed and
     *             giving the user the oppurtunity to check the contents of the output file.
     */
    private void testFile( boolean wait )  {
        String mesg                         =   null;
        mesg                                =   "Test file " + this.OUTFILE;
        if ( this.file.exists() && !this.file.isDirectory() ) {
            System.out.println( mesg + " exists, and the size is " + this.file.length() );
            if ( this.remove ) {
                if ( wait ) {
                    System.out.print( "\nWaiting for you to check the contents of the file." +
                            "\nPress ENTER when ready ... " );
                    try {
                        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
                        reader.readLine();
                    }   //  End-of try-clause
                    catch( IOException ioe ) {}     //  Catch-clause
                }   //  End-of if-branch
                System.out.println( "\nRemoving the test file." );
                this.file.delete();
            }   //  End-of if-branch
        }   //  End-of if-branch
        else {
            mesg                            +=  " does not exist";
        }   //  End-of else
        System.out.println( mesg );
    }   //  End-of method testFile


    /**
     * Test class for testing the ProcessOutputFile data package class.
     */
    @Override
    public void testClass() {

        Products prods                      =   new Products();
        prods.addProduct( new Product( "blouse", "12345", "men's blue summer blouse", "blouses", 21.90 ) );
        prods.addProduct( new Product( "shorts", "234567", "short pants for men, color white", "pants", 17.80) );

        try {
            //  Test if file already exists
            this.testFile( false );
            ProcessOutputFile pof = new ProcessOutputFile( this.OUTFILE, header, prods );
            pof.processOutFile();
            //  Test if file exists
            this.testFile( true );
        }   //  End-of try-clause
        catch( ProductException pe ) {
            System.out.println( pe.getMessage() );
        }   //  End-of catch-clause

    }   //  End-of testClass


    /**
     * Used to execute test of object of ProcessOutputFile class.
     * @param args Not used.
     * @throws ProductException Propagated by methods of the ProcessOutputFile class.
     */
    public static void main( String[] args ) throws ProductException {

        TestClass_ProcessOutputFile tc      =   new TestClass_ProcessOutputFile();
        tc.testClass();

    }   //  End-of method main


}   //  End-of class TestClass_ProcessOutputFile