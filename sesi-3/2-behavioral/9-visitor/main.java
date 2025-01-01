// Visitor interface
interface Visitor {
    void visit(ElementA element);
    void visit(ElementB element);
}

// Concrete Visitor
class ConcreteVisitor implements Visitor {
    @Override
    public void visit(ElementA element) {
        System.out.println("Visiting ElementA");
        element.operationA();
    }

    @Override
    public void visit(ElementB element) {
        System.out.println("Visiting ElementB");
        element.operationB();
    }
}

// Element interface
interface Element {
    void accept(Visitor visitor);
}

// Concrete Element A
class ElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA() {
        System.out.println("Operation A");
    }
}

// Concrete Element B
class ElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationB() {
        System.out.println("Operation B");
    }
}

// Main class to demonstrate the Visitor pattern
public class Main {
    public static void main(String[] args) {
        Element[] elements = { new ElementA(), new ElementB() };
        Visitor visitor = new ConcreteVisitor();

        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}