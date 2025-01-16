public class PaymentProcessor {

    public double calculateTotal(double price, double taxRate, double discountRate) {
        double tax = calculateTax(price, taxRate);
        double discount = calculateDiscount(price, discountRate);
        double total = price + tax - discount;

        total -= applyAdditionalDiscount(total);

        return total;
    }

    private double calculateTax(double price, double taxRate) {
        return price * taxRate;
    }

    private double calculateDiscount(double price, double discountRate) {
        return price * discountRate;
    }

    private double applyAdditionalDiscount(double total) {
        if (total > 1000) {
            return 50;
        } else if (total > 500) {
            return 20;
        }
        return 0;
    }

    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        double price = 1200.0;
        double taxRate = 0.1;
        double discountRate = 0.2;

        double totalPayment = paymentProcessor.calculateTotal(price, taxRate, discountRate);

        System.out.println("Total payment: " + totalPayment);
    }
}