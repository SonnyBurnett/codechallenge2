package data;

import domain.Product;
import domain.Products;
import test.TestClass_ProcessOutputFile;

import java.io.*;
import java.text.DecimalFormat;

public class ProcessOutputFile {

    private String filePath                 =   null;
    private Products products               =   null;
    private BufferedWriter bw               =   null;
    private String productsHeader           =   null;


    public ProcessOutputFile() {}

    /**
     *
     * @param filePath Provides the filePath of the output file.
     * @param productsHeader Hold the string of the header row content of the output file
     * @param products Contains the values of the products to be written to the output file.
     */
    public ProcessOutputFile( String filePath, String productsHeader, Products products ) {
        this.filePath                       =   filePath;
        this.productsHeader                 =   productsHeader;
        this.products                       =   products;
    }   //  End-of constructor


    /**
     *
     * @param filePath Provides the filePath of the output file.
     */
    public void setFilePath(  String filePath ) {
        this.filePath                       =   filePath;
    }


    /**
     *
     * @param productsHeader Hold the string of the header row content of the output file
     */
    public void setProductsHeader( String productsHeader ) {
        this.productsHeader                 =   productsHeader;
    }


    /**
     *
     * @param products Contains the values of the products to be written to the output file.
     */
    public void setProducts( Products products ) {
        this.products                       =   products;
    }

    /**
     *
     * @return true if opening or creating the output file succeeded, false otherwise.
     * @throws ProductException Thrown when opening or creating the output file failed and raised and IOException.
     */
    private boolean openOutFile() throws ProductException {
        boolean result                      =   false;
        try {
            File outFile                    =   new File( this.filePath );
            FileOutputStream fos            =   new FileOutputStream( outFile );
            bw                              =   new BufferedWriter( new OutputStreamWriter( fos ) );
            result                          =   true;
        }   //  End-of try-clause
        catch ( IOException ioe ) {
            throw new ProductException( "Unable to open file " + filePath  + ioe.getMessage() );
        }   //  End-of catch-clause
        return result;
    }   //  End-of method openOutFile


    /**
     * Method to write the data stored in object products to the specified output file.
     * @throws ProductException Thrown when writing to the output file fails and raises an IOException.
     */
    public void processOutFile() throws ProductException  {
        try {
            this.openOutFile();
            String line = null;
            DecimalFormat df = new DecimalFormat( "#0.00" );
            if ( this.products != null ) {
                this.bw.write( this.productsHeader + "\n" );
                for ( Product prod : this.products.getProductList() ) {
                    line = "";
                    // productId, name,  description, price, category
                    line += prod.getId() + ", ";
                    line += prod.getName() + ", ";
                    line += prod.getDescription() + ", ";
                    line += df.format( prod.getPrice() ) + ", ";
                    line += prod.getCategory() + "\n";
                    this.bw.write( line );
                    System.out.print( line );
                }   //  End-of for-loop
            }   //  End-of if-branch
        }   //  End-of try-clause
        catch( IOException ioe ) {
            throw new ProductException( "Unable to write to file " + filePath  + ioe.getMessage() );
        }   //  End-of catch-clause
        finally {
            try {
                bw.close();
            }   //  End-of try-clause
            catch( IOException ioe ) {}     //  catch-clause
        }   //  End-of finally-clause
    }   //  End-of method processOutFile


    /**
     * For testing purposes only.
     * @param args Not used!
     */
    public static void main( String[] args ) throws ProductException, IOException {

        TestClass_ProcessOutputFile tc      =   new TestClass_ProcessOutputFile();
        tc.testClass();

    }   //  End-of method main


}   //  End-of class
