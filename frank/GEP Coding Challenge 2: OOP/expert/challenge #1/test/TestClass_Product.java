package test;

import domain.Product;


/**
 * Test class for Product domain package class.
 */
public class TestClass_Product implements ITestClass {

    /**
     * Creates objects of Class Product and shows the values, using the Product toString method.
     */
    @Override
    public void testClass()  {

        //  Create 2 Product objects on 2 different ways.
        Product prod01            =      new Product( "blouse", "12345", "men's blue summer blouse", "blouses", 21.90);
        Product prod02            =      new Product();
        prod02.newProduct("shorts", "234567", "short pants for men, color white", "pants", 17.80);

        System.out.println( prod01.getName() + ": $" + prod01.getPrice() );
        System.out.println( prod02.getName() + ": $" + prod02.getPrice() );
        System.out.println( prod01.toString() );
        System.out.println( prod02.toString() );

    }   //  End-of method testClass


    /**
     * Used to execute test of object of Product class.
     * @param args Not used.
     */
    public static void main( String[] args ) {

        TestClass_Product tc        =   new TestClass_Product();
        tc.testClass();

    }   //  End-of method main


}   //  End-of class TestClass_Product