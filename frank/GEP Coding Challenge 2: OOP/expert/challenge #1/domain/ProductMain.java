package domain;

import data.ProcessInputFile;
import data.ProcessOutputFile;
import data.ProductException;

/**
 * Class ProductMain
 */
public class ProductMain {

    // private final String PATH2INFILE    =   "C:\\Users\\fgmni\\IdeaProjects\\GEP Code Challenge OOP\\src\\data\\001-experts-inputs.csv";
    // private final String PATH2OUTFILE   =   "C:\\Users\\fgmni\\IdeaProjects\\GEP Code Challenge OOP\\src\\data\\001-experts-outputs.csv-OLD";
    private final String PATH2INFILE    =   System.getProperty( "user.dir" ) + "\\src\\data\\001-experts-inputs.csv";
    private final String PATH2OUTFILE   =   System.getProperty( "user.dir" ) + "\\src\\data\\001-experts-outputs.csv";

    private String productsHeader       =   null;
    private Products products           =   null;
    private ProcessInputFile pif        =   null;
    private ProcessOutputFile pof       =   null;


    public ProductMain() {
        // pif                             =   new ProcessInputFile( this.PATH2INFILE );
        new ProcessInputFile( this.PATH2INFILE );
    }   //  End-of constructor


    public ProductMain( String inFile ) {
        pif                             =   new ProcessInputFile( inFile );
        if ( pif != null ) {
            this.productsHeader         =   pif.getProductsHeader();
        }
    }   //  End-of constructor


    /**
     *
     * @throws ProductException
     */
    public void readData() throws ProductException {
        pif.processInFile();
        this.products                   =   pif.getProducts();
    }   //  End-of method readData


    public void writeData() throws ProductException {
        pof                             =   new ProcessOutputFile(  this.PATH2OUTFILE,
                                                                    this.productsHeader,
                                                                    this.products );
        pof.processOutFile();
    }   //  End-of method writeData


    public void writeData( String outFile ) throws ProductException {
        pof                             =   new ProcessOutputFile(  outFile,
                                                                    this.productsHeader,
                                                                    this.products );
        pof.processOutFile();
    }   //  End-of method writeData



    public static void main( String[] args ) {
        ProductMain pm                  =   new ProductMain();
        try {
            pm.readData();
            pm.writeData();
        }   //  End-of try-clause
        catch( ProductException pe ) {
            System.out.println( pe.getMessage() );
        }   //  End-of catch-clause
    }   //  End=of main

}   //  End-of class
