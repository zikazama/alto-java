import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

// Flyweight interface
interface TextFormat {
    void apply(String text);
}

// Concrete Flyweight
class FontFormat implements TextFormat {
    private final String fontName;
    private final int fontSize;
    private final String color;
    private final boolean isBold;
    private final boolean isItalic;
    
    public FontFormat(String fontName, int fontSize, String color, 
                     boolean isBold, boolean isItalic) {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.color = color;
        this.isBold = isBold;
        this.isItalic = isItalic;
    }
    
    @Override
    public void apply(String text) {
        StringBuilder style = new StringBuilder();
        style.append("Text: '").append(text).append("' with style [");
        style.append("font: ").append(fontName);
        style.append(", size: ").append(fontSize);
        style.append(", color: ").append(color);
        if (isBold) style.append(", bold");
        if (isItalic) style.append(", italic");
        style.append("]");
        
        System.out.println(style);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        FontFormat that = (FontFormat) o;
        return fontSize == that.fontSize &&
               isBold == that.isBold &&
               isItalic == that.isItalic &&
               fontName.equals(that.fontName) &&
               color.equals(that.color);
    }
    
    @Override
    public int hashCode() {
        int result = fontName.hashCode();
        result = 31 * result + fontSize;
        result = 31 * result + color.hashCode();
        result = 31 * result + (isBold ? 1 : 0);
        result = 31 * result + (isItalic ? 1 : 0);
        return result;
    }
}

// Flyweight Factory
class TextFormatFactory {
    private static final Map<String, TextFormat> formats = new HashMap<>();
    
    public static TextFormat getFormat(String fontName, int fontSize, 
                                     String color, boolean isBold, boolean isItalic) {
        String key = String.format("%s_%d_%s_%b_%b", 
                                 fontName, fontSize, color, isBold, isItalic);
        
        return formats.computeIfAbsent(key, 
            k -> new FontFormat(fontName, fontSize, color, isBold, isItalic));
    }
    
    public static int getTotalFormats() {
        return formats.size();
    }
}

// Context class that uses the flyweight
class FormattedText {
    private final String text;
    private final TextFormat format;
    
    public FormattedText(String text, TextFormat format) {
        this.text = text;
        this.format = format;
    }
    
    public void display() {
        format.apply(text);
    }
}

// Document class that uses formatted text
class Document {
    private final List<FormattedText> contents = new ArrayList<>();
    
    public void addText(String text, String fontName, int fontSize, 
                       String color, boolean isBold, boolean isItalic) {
        TextFormat format = TextFormatFactory.getFormat(fontName, fontSize, 
                                                      color, isBold, isItalic);
        contents.add(new FormattedText(text, format));
    }
    
    public void display() {
        for (FormattedText content : contents) {
            content.display();
        }
    }
}

// Text Editor that uses the Document
class TextEditor {
    private final Document document;
    
    public TextEditor() {
        this.document = new Document();
    }
    
    public void createDocument() {
        // Adding text with various formats
        document.addText("Chapter 1:", "Arial", 16, "black", true, false);
        document.addText("Once upon a time...", "Times New Roman", 12, "black", false, false);
        document.addText("Important note:", "Arial", 12, "red", true, false);
        document.addText("Remember this!", "Arial", 12, "red", true, false);
        document.addText("Italic text", "Times New Roman", 12, "black", false, true);
        document.addText("Bold and Italic", "Times New Roman", 12, "black", true, true);
    }
    
    public void displayDocument() {
        document.display();
    }
}

// Main class to demonstrate the Flyweight pattern
public class Main {
    public static void main(String[] args) {
        demonstrateTextEditor();
        demonstrateMemoryUsage();
    }
    
    private static void demonstrateTextEditor() {
        System.out.println("=== Text Editor Demonstration ===");
        TextEditor editor = new TextEditor();
        editor.createDocument();
        
        System.out.println("\nDisplaying document:");
        editor.displayDocument();
        
        System.out.println("\nTotal unique formats created: " + 
                          TextFormatFactory.getTotalFormats());
    }
    
    private static void demonstrateMemoryUsage() {
        System.out.println("\n=== Memory Usage Demonstration ===");
        
        // Create a large document with repeated formats
        Document largeDoc = new Document();
        String[] texts = {
            "Regular text", "Important notice", "Note", "Reminder",
            "Section", "Paragraph", "Quote", "Citation"
        };
        
        // Add many text entries with the same few formats
        for (int i = 0; i < 1000; i++) {
            String text = texts[i % texts.length] + " " + i;
            
            // Alternate between a few format combinations
            if (i % 3 == 0) {
                largeDoc.addText(text, "Arial", 12, "black", false, false);
            } else if (i % 3 == 1) {
                largeDoc.addText(text, "Arial", 12, "red", true, false);
            } else {
                largeDoc.addText(text, "Times New Roman", 14, "blue", false, true);
            }
        }
        
        System.out.println("Created 1000 text entries");
        System.out.println("Total unique formats used: " + 
                          TextFormatFactory.getTotalFormats());
        System.out.println("Memory saved by reusing formats!");
    }
}

// Additional utility class to demonstrate format statistics
class FormatStatistics {
    public static void printFormatStats() {
        System.out.println("\n=== Format Statistics ===");
        System.out.println("Total unique format combinations: " + 
                          TextFormatFactory.getTotalFormats());
        
        // Calculate theoretical memory savings
        int totalObjects = 1000;  // assuming 1000 text entries
        int uniqueFormats = TextFormatFactory.getTotalFormats();
        int theoreticalMemoryWithoutFlyweight = totalObjects * 5 * 4;  // rough estimate
        int theoreticalMemoryWithFlyweight = uniqueFormats * 5 * 4;    // rough estimate
        
        System.out.println("Theoretical memory without Flyweight: " + 
                          theoreticalMemoryWithoutFlyweight + " bytes");
        System.out.println("Theoretical memory with Flyweight: " + 
                          theoreticalMemoryWithFlyweight + " bytes");
        System.out.println("Theoretical memory saved: " + 
                          (theoreticalMemoryWithoutFlyweight - theoreticalMemoryWithFlyweight) + 
                          " bytes");
    }
}
