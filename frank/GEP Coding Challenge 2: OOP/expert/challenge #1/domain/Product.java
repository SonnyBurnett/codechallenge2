package domain;

import test.TestClass_Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Product implements IProduct {

    // Attribute declaration and initialisation

    private String name             =      null;
    private String id               =      null;
    private String description      =      null;
    private String category         =      null;
    private double price            =      0;


    /**
     * Parameterless constructor.
     */
    public Product() {}


    /**
     * Parameterized constructor.
     * @param name Name of the product.
     * @param id Unique identifier of the product.
     * @param description A short description of the product.
     * @param category The category of the product.
     * @param price The price of the item in US Dollars.
     */
    public Product( String name, String id, String description, String category, double price ) {
        this.name                   =      name;
        this.id                     =      id;
        this.description            =      description;
        this.category               =      category;
        this.price                  =      price;
    }


    /**
     *
     * @param name Name of the product.
     * @param id Unique identifier of the product.
     * @param description A short description of the product.
     * @param category The category of the product.
     * @param price The price of the item in US Dollars.
     */
    @Override
    public void newProduct( String name, String id, String description, String category, double price ) {
        this.name                   =      name;
        this.id                     =      id;
        this.description            =      description;
        this.category               =      category;
        this.price                  =      price;
    }   //  End-of method newProduct


    /**
     *
     * @return The price of the product.
     */
    @Override
    public double getPrice() {
        return this.price;
    }   //  End-of method getPrice


    /**
     *
     * @return The name of the product.
     */
    @Override
    public String getName() {
        return this.name;
    }   //  End-if method getName


    /**
     *
     * @return Returns the id of the product.
     */
    @Override
    public String getId() {
        return this.id;
    }   //  End-of method getId


    /**
     *
     * @return Returns the description of the product.
     */
    @Override
    public String getDescription() {
        return this.description;
    }   //  End-of method getDescription


    /**
     *
     * @return The category of the product.
     */
    @Override
    public String getCategory() {
        return this.category;
    }   //  End-of method getCategory


    /**
     *
     * @return A string displaying the price in 2 decimals.
     */
    public String price2String() {
        String result               =   "";
        NumberFormat formatter      =   new DecimalFormat( "#0.00" );
        result                      =   formatter.format( this.price );
        return result;
    }   //  End-of method price2String


    /**
     *
     * @return A string value presenting the values of all the products of the class.
     */
    public String toString() {
        String result               =      "";
        result                     +=     "Class name: " + this.getClass().getName();
        result                     +=     "\nName: " + this.name;
        result                     +=     "\nId: " + this.id;
        result                     +=     "\nDescription: " + this.description;
        result                     +=     "\nCategory: " + this.category;
        result                     +=     "\nPrice: $" + this.price2String();
        return result;
    }   //  End-of method toString


    /**
     * Only used for testing purposes.
     * @param args Not used.
     */
    public static void main( String[] args ) {

        TestClass_Product tc        =   new TestClass_Product();
        tc.testClass();

    }   //  End-of main


}   //  End-of class
