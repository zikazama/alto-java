// Prototype interface
interface Prototype extends Cloneable {
    Prototype clone();
}

// Concrete Prototype 1
class ConcretePrototype1 implements Prototype {
    private String name;

    public ConcretePrototype1(String name) {
        this.name = name;
    }

    @Override
    public Prototype clone() {
        try {
            return (ConcretePrototype1) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }

    @Override
    public String toString() {
        return "ConcretePrototype1{name='" + name + "'}";
    }
}

// Concrete Prototype 2
class ConcretePrototype2 implements Prototype {
    private String name;

    public ConcretePrototype2(String name) {
        this.name = name;
    }

    @Override
    public Prototype clone() {
        try {
            return (ConcretePrototype2) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }

    @Override
    public String toString() {
        return "ConcretePrototype2{name='" + name + "'}";
    }
}

// Main class to demonstrate the Prototype pattern
public class Main {
    public static void main(String[] args) {
        ConcretePrototype1 prototype1 = new ConcretePrototype1("Prototype 1");
        ConcretePrototype1 clone1 = (ConcretePrototype1) prototype1.clone();
        System.out.println(prototype1);
        System.out.println(clone1);

        ConcretePrototype2 prototype2 = new ConcretePrototype2("Prototype 2");
        ConcretePrototype2 clone2 = (ConcretePrototype2) prototype2.clone();
        System.out.println(prototype2);
        System.out.println(clone2);
    }
}