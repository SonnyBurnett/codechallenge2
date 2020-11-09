package ex02;

public class IllegalMove extends RuntimeException {
    public IllegalMove (String message) {
        super(message + " Illegal move.");
    }
}
