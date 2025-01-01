// Abstract Product A
interface ProductA {
    void use();
}

// Abstract Product B
interface ProductB {
    void use();
}

// Concrete Product A1
class ConcreteProductA1 implements ProductA {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductA1");
    }
}

// Concrete Product A2
class ConcreteProductA2 implements ProductA {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductA2");
    }
}

// Concrete Product B1
class ConcreteProductB1 implements ProductB {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductB1");
    }
}

// Concrete Product B2
class ConcreteProductB2 implements ProductB {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductB2");
    }
}

// Abstract Factory
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

// Concrete Factory 1
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

// Concrete Factory 2
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}

// Main class to demonstrate the Abstract Factory pattern
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();
        productA1.use(); // Using ConcreteProductA1
        productB1.use(); // Using ConcreteProductB1

        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();
        productA2.use(); // Using ConcreteProductA2
        productB2.use(); // Using ConcreteProductB2
    }
}