import java.io.IOException;

// Legacy Payment System Interface
interface LegacyPayment {
    void processPayment(double amount);
}

// Modern Payment System
class ModernPaymentSystem {
    public void makePayment(String currency, double amount) {
        System.out.println("Processing " + currency + " payment of " + amount + " using modern system");
    }
    
    public void validatePayment() {
        System.out.println("Validating payment in modern system");
    }
}

// Adapter to make ModernPaymentSystem work with LegacyPayment interface
class PaymentAdapter implements LegacyPayment {
    private final ModernPaymentSystem modernPaymentSystem;
    
    public PaymentAdapter(ModernPaymentSystem modernPaymentSystem) {
        this.modernPaymentSystem = modernPaymentSystem;
    }
    
    @Override
    public void processPayment(double amount) {
        // Adapt the modern payment system to work with legacy interface
        modernPaymentSystem.validatePayment();
        modernPaymentSystem.makePayment("USD", amount);
    }
}

// Client code using the adapter
class PaymentProcessor {
    private final LegacyPayment paymentSystem;
    
    public PaymentProcessor(LegacyPayment paymentSystem) {
        this.paymentSystem = paymentSystem;
    }
    
    public void makePayment(double amount) {
        paymentSystem.processPayment(amount);
    }
}

// Main class to demonstrate the adapter pattern
public class Main {
    public static void main(String[] args) {
        // Create instance of modern payment system
        ModernPaymentSystem modernSystem = new ModernPaymentSystem();
        
        // Create adapter
        LegacyPayment adapter = new PaymentAdapter(modernSystem);
        
        // Create payment processor using adapter
        PaymentProcessor processor = new PaymentProcessor(adapter);
        
        // Process payment using legacy interface
        processor.makePayment(100.00);
    }
}
