// State interface
interface State {
    void handle(Context context);
}

// Concrete State A
class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("State A handling request.");
        context.setState(new ConcreteStateB());
    }
}

// Concrete State B
class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("State B handling request.");
        context.setState(new ConcreteStateA());
    }
}

// Context class
class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handle(this);
    }
}

// Main class to demonstrate the State pattern
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());

        context.request(); // State A handling request.
        context.request(); // State B handling request.
        context.request(); // State A handling request.
        context.request(); // State B handling request.
    }
}