public class CalculateAreaDry {
    // Metode untuk menghitung area
    public static double calculateRectangleArea(double length, double width) {
        return length * width;
    }

    public static void main(String[] args) {
        double length1 = 5.0;
        double width1 = 10.0;
        double length2 = 7.5;
        double width2 = 4.0;

        // Menggunakan metode untuk menghitung area
        System.out.println("Area of rectangle 1: " + calculateRectangleArea(length1, width1));
        System.out.println("Area of rectangle 2: " + calculateRectangleArea(length2, width2));
    }
}
