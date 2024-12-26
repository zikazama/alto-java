public public class GreetingWithoutKiss {
    public static void main(String[] args) {
        String name = "Fauzi";
        String message = generateGreetingMessage(name);
        if (message.length() > 0) {
            if (message.contains(" ")) {
                String[] parts = message.split(" ");
                String firstName = parts[0];
                String lastName = parts[1];
                String fullGreeting = "Hello, " + firstName + " " + lastName + "!";
                System.out.println(fullGreeting);
            } else {
                System.out.println(message);
            }
        }
    }

    public static String generateGreetingMessage(String name) {
        // Complex logic for generating a message
        if (name != null && !name.isEmpty()) {
            return "Hello " + name;
        }
        return "";
    }
}
