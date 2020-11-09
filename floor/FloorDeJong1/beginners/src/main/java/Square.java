public class Square extends Rectangle {

    public Square (double length) {
        super(length, length);
    }

    @Override
    public String toString() {
        return "Square: " + getLength() + "x" + getLength();
    }
}
