// Handler interface
abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String request);
}

// Concrete Handler 1
class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Request1")) {
            System.out.println("ConcreteHandler1 handled the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Concrete Handler 2
class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Request2")) {
            System.out.println("ConcreteHandler2 handled the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Concrete Handler 3
class ConcreteHandler3 extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Request3")) {
            System.out.println("ConcreteHandler3 handled the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Main class to demonstrate the Chain of Responsibility pattern
public class Main {
    public static void main(String[] args) {
        // Create handlers
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        // Set the chain of responsibility
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        // Test the chain
        handler1.handleRequest("Request1");
        handler1.handleRequest("Request2");
        handler1.handleRequest("Request3");
        handler1.handleRequest("UnknownRequest");
    }
}