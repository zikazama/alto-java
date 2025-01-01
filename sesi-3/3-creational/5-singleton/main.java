// Singleton class
class Singleton {
    // Private static instance of the singleton class
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
        // Initialization code here
    }

    // Public static method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate functionality
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

// Main class to demonstrate the Singleton pattern
public class Main {
    public static void main(String[] args) {
        // Get the only instance of Singleton
        Singleton singleton = Singleton.getInstance();

        // Show a message
        singleton.showMessage();
    }
}