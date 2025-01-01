import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String message;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}

// Concrete Observer 1
class ConcreteObserver1 implements Observer {
    private String name;

    public ConcreteObserver1(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

// Concrete Observer 2
class ConcreteObserver2 implements Observer {
    private String name;

    public ConcreteObserver2(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

// Main class to demonstrate the Observer pattern
public class Main {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer1 = new ConcreteObserver1("Observer 1");
        Observer observer2 = new ConcreteObserver2("Observer 2");

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.setMessage("Hello, Observers!");
        subject.setMessage("Another message");
    }
}