// Strategy interface
interface Strategy {
    int execute(int a, int b);
}

// Concrete Strategy for addition
class AdditionStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

// Concrete Strategy for subtraction
class SubtractionStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

// Concrete Strategy for multiplication
class MultiplicationStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}

// Context class
class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// Main class to demonstrate the Strategy pattern
public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        // Using addition strategy
        context.setStrategy(new AdditionStrategy());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        // Using subtraction strategy
        context.setStrategy(new SubtractionStrategy());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        // Using multiplication strategy
        context.setStrategy(new MultiplicationStrategy());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}