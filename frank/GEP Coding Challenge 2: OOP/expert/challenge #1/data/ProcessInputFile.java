package data;

import domain.Product;
import domain.Products;
import test.TestClass_ProcessOutputFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ProcessInputFile {


    private final double DOLLAR2EURO_RATE   =      0.85;

    private String filePath                 =   null;
    private FileReader fr                   =   null;
    private BufferedReader reader           =   null;
    private Products products               =   null;
    private String productsHeader           =   null;


    /**
     *
     * @param filePath Value containing the full path to the input file
     */
    public ProcessInputFile( String filePath ) {
        this.filePath                       =      filePath;
        this.products                       =      new Products();
    }   //  End-of constructor


    /**
     * Method to open file for reading.
     * @return true if file is opened successfully, false otherwise.
     * @throws ProductException Thrown when opening the specified file failed and has raised an IOException.
     */
    private boolean openInFile() throws ProductException {
        boolean      result                 =      false;
        try {
            File inFile                     =      new File( this.filePath );
            this.fr                         =      new FileReader( inFile );
            this.reader                     =      new BufferedReader( fr );
            result                          =      true;
        }   //  End-of try-clause
        catch ( IOException ioe ) {
            throw new ProductException( "Unable to open file " + filePath  + ioe.getMessage() );
        }   //  End-of catch-clause
        return result;
    }   //  End-of method openInFile


    /**
     * Method for reading an opened input file.
     * Calls method openInFile to open the input file
     * @throws ProductException Thrown when reading the input file failed and has raised an IOException
     */
    public void processInFile() throws ProductException {

        String line                         =      "";
        Product product                     =      null;
        double price                        =      0.0;
        String name                         =      null;
        String id                           =      null;
        String description                  =      null;
        String category                     =      null;
        String strPrice                     =      null;
        String[] fields                     =      null;

        try {
            this.openInFile();
            this.productsHeader              =      this.reader.readLine();  //     Skip header line
            while ( line != null ) {
                line                        =      this.reader.readLine();
                if ( line != null ) {
                    // productId, name,  description, price, category
                    fields                  =   line.split( "," );
                    id                      =   fields[ 0 ].trim();
                    name                    =   fields[ 1 ].trim();
                    description             =   fields[ 2 ].trim();
                    strPrice                =   fields[ 3 ].trim();
                    category                =   fields[ 4 ].trim();
                    price                   =   Double.parseDouble( strPrice ) * this.DOLLAR2EURO_RATE;
                    // String name, String id, String description, String category, double price
                    product = new Product( name, id, description, category, price );
                    products.addProduct( product );
                }   //  End-of if-branch
            }   //  End-of while-loop
        }   //  End-of try-clause
        catch( IOException ioe ) {
            throw new ProductException( "Unable to read from file " + filePath  + ioe.getMessage() );
        }   //  End-of catch-clause
        finally {
            try {
                fr.close();
            }   //  End-of try-clause
            catch( IOException ieo ) {}
        }   //  End-of finally-clause

    }   //  End-of method processInFile


    /**
     *
     * @return Value of attribute prodoctsheader, header and first row of the input file.
     */
    public String getProductsHeader() {
        return this.productsHeader;
    }   //  End-of method getProductsHeader


    /**
     *
     * @return The value of attribute products
     */
    public Products getProducts() {
        return this.products;
    }   //  End-of method getProducts


    /**
     * Only used for testing.
     * @param args Not used.
     * @throws ProductException File handling operations like opening and reading from input file.
     */
    public static void main( String[] args ) throws ProductException {

        TestClass_ProcessOutputFile tc  =   new TestClass_ProcessOutputFile();
        tc.testClass();

    }   //  End-of main


}   //  End-of class
