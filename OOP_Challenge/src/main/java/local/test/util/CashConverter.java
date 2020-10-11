package local.test.util;

public class CashConverter {
    private static final double CONVERSION = 0.85;
    public static double ConvertDollarToEuro(int dollar){
        return dollar*CONVERSION;
    }

}
