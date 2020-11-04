package test.local.util;

public class CashConverter {
    private CashConverter (){
        // empty constructor, no instantiation of this class
    }
    private static final double CONVERSION = 0.85;

    public static double ConvertDollarToEuro(int dollar) {
        return dollar * CONVERSION;
    }

}
