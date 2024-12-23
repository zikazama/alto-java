public class PaymentProcessor {
    public double calculateTotal(double price, double taxRate, double discountRate) {
        double tax = price * taxRate;
        double discount = price * discountRate;
        double total = price + tax - discount;
        
        if (total > 1000) {
            total = total - 50;
        } else if (total > 500) {
            total = total - 20;
        }
        
        return total;
    }`

    public double calculateTax(double price, double taxRate) {
        return price * taxRate;
    }

    public double calculateDiscount(double price, double discountRate) {
        return price * discountRate;
    }

    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        double price = 1200.0;
        double taxRate = 0.1;
        double discountRate = 0.2;
        
        double tax = processor.calculateTax(price, taxRate);
        double discount = processor.calculateDiscount(price, discountRate);
        double total = processor.calculateTotal(price, taxRate, discountRate);
        
        System.out.println("Total payment: " + total);
    }
}
