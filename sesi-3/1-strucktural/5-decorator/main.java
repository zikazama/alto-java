// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    
    @Override
    public double getCost() {
        return 2.0;
    }
}

// Base Decorator
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Milk";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Whipped Cream";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.7;
    }
}

class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Caramel";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.6;
    }
}

class ChocolateDecorator extends CoffeeDecorator {
    private final int chocolateShots;
    
    public ChocolateDecorator(Coffee coffee, int chocolateShots) {
        super(coffee);
        this.chocolateShots = chocolateShots;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + 
               ", with " + chocolateShots + " shot(s) of Chocolate";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + (0.4 * chocolateShots);
    }
}

class VanillaDecorator extends CoffeeDecorator {
    public VanillaDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Vanilla";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.3;
    }
}

// Order builder to make it easier to create complex coffee orders
class CoffeeOrderBuilder {
    private Coffee coffee;
    
    public CoffeeOrderBuilder() {
        this.coffee = new SimpleCoffee();
    }
    
    public CoffeeOrderBuilder addMilk() {
        coffee = new MilkDecorator(coffee);
        return this;
    }
    
    public CoffeeOrderBuilder addWhippedCream() {
        coffee = new WhippedCreamDecorator(coffee);
        return this;
    }
    
    public CoffeeOrderBuilder addCaramel() {
        coffee = new CaramelDecorator(coffee);
        return this;
    }
    
    public CoffeeOrderBuilder addChocolate(int shots) {
        coffee = new ChocolateDecorator(coffee, shots);
        return this;
    }
    
    public CoffeeOrderBuilder addVanilla() {
        coffee = new VanillaDecorator(coffee);
        return this;
    }
    
    public Coffee build() {
        return coffee;
    }
}

// Main class to demonstrate the decorator pattern
public class Main {
    public static void main(String[] args) {
        // Example 1: Creating a simple coffee
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Order 1:");
        printCoffeeDetails(simpleCoffee);
        
        // Example 2: Creating a coffee with milk and caramel
        Coffee milkCaramelCoffee = new CaramelDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("\nOrder 2:");
        printCoffeeDetails(milkCaramelCoffee);
        
        // Example 3: Creating a complex coffee using multiple decorators
        Coffee complexCoffee = new WhippedCreamDecorator(
                                new ChocolateDecorator(
                                    new VanillaDecorator(
                                        new MilkDecorator(
                                            new SimpleCoffee()
                                        )
                                    ), 2
                                )
                             );
        System.out.println("\nOrder 3:");
        printCoffeeDetails(complexCoffee);
        
        // Example 4: Using the builder pattern for a cleaner syntax
        Coffee builderCoffee = new CoffeeOrderBuilder()
            .addMilk()
            .addCaramel()
            .addWhippedCream()
            .addChocolate(2)
            .addVanilla()
            .build();
        
        System.out.println("\nOrder 4 (using builder):");
        printCoffeeDetails(builderCoffee);
    }
    
    private static void printCoffeeDetails(Coffee coffee) {
        System.out.println("Description: " + coffee.getDescription());
        System.out.printf("Cost: $%.2f%n", coffee.getCost());
    }
}
