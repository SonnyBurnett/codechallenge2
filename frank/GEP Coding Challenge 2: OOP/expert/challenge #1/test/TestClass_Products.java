package test;

import domain.Product;
import domain.Products;


/**
 * Test class for testing the Products data package class.
 */
public class TestClass_Products implements ITestClass {

    /**
     *  Uses the toString method of the Products and Product classes to show the content of the created objects.
     */
    @Override
    public void testClass() {

        Products products           =      new Products();
        Product prod00              =      new Product( "woollen socks", "01345", "women's winter socks, black", "socks", 9.90 );
        Product prod01              =      new Product( "blouse", "12345", "men's blue summer blouse", "blouses", 21.90 );
        Product prod02              =      new Product();
        prod02.newProduct( "shorts", "234567", "short pants for men, color white", "pants", 17.80 );

        products.addProduct( prod00 );
        products.addProduct( prod01 );
        products.addProduct( prod02 );

        System.out.println( products.toString() );

    }   //  End-of method testClass


    public static void main( String[] args ) {

        TestClass_Products tc       =   new TestClass_Products();
        tc.testClass();

    }


}   //  End-of class TestClass_Products