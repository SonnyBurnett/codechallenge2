public class Rectangle implements Figure2D {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return calculateArea(this.length, this.width);
    }

    @Override
    public double getCircumference() {
        return calculateCircumference(this.length, this.width);
    }

    @Override
    public String toString() {
        return "Rectangle: " + this.length + "x" + this.width;
    }

    public static double calculateArea(double length, double width) {
        return length * width;
    }

    public static double calculateCircumference(double length, double width) {
        return 2*length + 2*width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
