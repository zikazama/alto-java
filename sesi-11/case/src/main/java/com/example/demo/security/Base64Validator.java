import java.util.Base64;

public class Base64Validator {
    public static void main(String[] args) {
        String secretKey = "U29tZVNlY3VyZUtleUNyZWF0ZWQgd2l0aCBPcGVuU1NM";

        try {
            Base64.getDecoder().decode(secretKey);
            System.out.println("Key is valid Base64.");
        } catch (IllegalArgumentException e) {
            System.err.println("Key is not valid Base64: " + e.getMessage());
        }
    }
}
