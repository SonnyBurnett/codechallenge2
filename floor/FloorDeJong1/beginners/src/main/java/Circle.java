public class Circle implements Figure2D {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return calculateArea(this.radius);
    }

    @Override
    public double getCircumference() {
        return calculateCircumference(this.radius);
    }

    @Override
    public String toString() {
        return "Circle: r=" + this.radius;
    }

    public static double calculateArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public static double calculateCircumference(double radius) {
        return 2*Math.PI*radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
