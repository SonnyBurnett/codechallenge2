package domain;

import test.TestClass_Products;

import java.util.ArrayList;

public class Products {

    private final double MIN_DOLLARPRICE   =      10.0;
    private ArrayList<Product> productList =      null;

    public Products() {
        this.productList                               =      new ArrayList<>();
    }   //  End-of constructor


    /**
     *
     * @param product Product object to be added to productList
     */
    public void addProduct( Product product ) {
        if ( product.getPrice() >= this.MIN_DOLLARPRICE ) {
            this.productList.add( product );
        }   //  End-of if-branch
    }   //  End-of method addProduct


    /**
     * Getter method for attribute productList.
     * @return Value of productList attribute.
     */
    public ArrayList<Product> getProductList() {
        return this.productList;
    }   //  End-of method getProductList


    /**
     *
     * @return String containing class name and the values of the attributes
     */
    public String toString() {
        String result               =      "";
        result                     +=     "Class name: " + this.getClass().getName();
        result                     +=     "\nNumber of products: " + this.productList.size();
        for ( Product prod : this.productList ) {
            result                 +=     "\n" + prod.toString();
        }
        return result;
    }   //  End-of method toString


    /**
     * Used for testing purposes only.
     * @param args Not used.
     */
    public static void main( String[] args ) {

        TestClass_Products tc               =   new TestClass_Products();
        tc.testClass();

    }   //  End-of main


}   //  End-of class
