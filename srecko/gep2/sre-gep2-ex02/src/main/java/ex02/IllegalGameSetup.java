package ex02;

public class IllegalGameSetup extends RuntimeException {
    public IllegalGameSetup (String message) {
        super(message + " Illegal game setup.");
    }
}