import java.util.ArrayList;
import java.util.List;

// Abstract class defining the template method
abstract class DataMiner {
    // Template method
    public final void mine(String path) {
        String data = readData(path);
        String[] records = parseData(data);
        List<Object> processedData = processData(records);
        analyzeData(processedData);
        sendReport();
        
        // Hook method - optional step
        if (shouldBackupData()) {
            backupData(data);
        }
    }
    
    // Abstract methods that must be implemented by subclasses
    protected abstract String readData(String path);
    protected abstract String[] parseData(String data);
    
    // Concrete methods that can be overridden if needed
    protected List<Object> processData(String[] records) {
        List<Object> processedData = new ArrayList<>();
        System.out.println("Processing " + records.length + " records...");
        
        for (String record : records) {
            // Default processing logic
            processedData.add(record.trim().toLowerCase());
        }
        
        return processedData;
    }
    
    protected void analyzeData(List<Object> data) {
        System.out.println("Analyzing " + data.size() + " processed records...");
        // Default analysis logic
        for (Object item : data) {
            System.out.println("Analyzed item: " + item);
        }
    }
    
    protected void sendReport() {
        System.out.println("Sending generic report...");
    }
    
    // Hook method - provides default behavior but can be overridden
    protected boolean shouldBackupData() {
        return true;
    }
    
    protected void backupData(String data) {
        System.out.println("Backing up data: " + data.substring(0, 
            Math.min(data.length(), 20)) + "...");
    }
}

// Concrete class for CSV files
class CSVDataMiner extends DataMiner {
    @Override
    protected String readData(String path) {
        System.out.println("Reading CSV file from: " + path);
        // Simulate reading CSV file
        return "name,age,city\nJohn,30,New York\nJane,25,Boston";
    }
    
    @Override
    protected String[] parseData(String data) {
        System.out.println("Parsing CSV data...");
        return data.split("\n");
    }
    
    @Override
    protected List<Object> processData(String[] records) {
        List<Object> processedData = new ArrayList<>();
        System.out.println("Processing CSV records...");
        
        // Skip header row
        for (int i = 1; i < records.length; i++) {
            String[] fields = records[i].split(",");
            processedData.add(new Person(fields[0], 
                                       Integer.parseInt(fields[1]), 
                                       fields[2]));
        }
        
        return processedData;
    }
    
    @Override
    protected void sendReport() {
        System.out.println("Sending CSV analysis report via email...");
    }
}

// Concrete class for PDF files
class PDFDataMiner extends DataMiner {
    @Override
    protected String readData(String path) {
        System.out.println("Reading PDF file from: " + path);
        // Simulate reading PDF file
        return "PDF Content: Some text extracted from PDF";
    }
    
    @Override
    protected String[] parseData(String data) {
        System.out.println("Parsing PDF data...");
        return data.split("\\s+");
    }
    
    @Override
    protected boolean shouldBackupData() {
        // Don't backup PDF files by default
        return false;
    }
}

// Concrete class for JSON files
class JSONDataMiner extends DataMiner {
    @Override
    protected String readData(String path) {
        System.out.println("Reading JSON file from: " + path);
        // Simulate reading JSON file
        return """
               {
                   "users": [
                       {"name": "John", "age": 30, "city": "New York"},
                       {"name": "Jane", "age": 25, "city": "Boston"}
                   ]
               }""";
    }
    
    @Override
    protected String[] parseData(String data) {
        System.out.println("Parsing JSON data...");
        // Simplified JSON parsing
        return data.split("\n");
    }
    
    @Override
    protected void analyzeData(List<Object> data) {
        System.out.println("Performing specialized JSON data analysis...");
        // Custom analysis for JSON data
        super.analyzeData(data);
    }
}

// Helper class for processed data
record Person(String name, int age, String city) {
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", city='" + city + "'}";
    }
}

// Main class to demonstrate the Template Method pattern
public class Main {
    public static void main(String[] args) {
        demonstrateCSVMining();
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        demonstratePDFMining();
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        demonstrateJSONMining();
    }
    
    private static void demonstrateCSVMining() {
        System.out.println("=== CSV Data Mining ===");
        DataMiner csvMiner = new CSVDataMiner();
        csvMiner.mine("data.csv");
    }
    
    private static void demonstratePDFMining() {
        System.out.println("=== PDF Data Mining ===");
        DataMiner pdfMiner = new PDFDataMiner();
        pdfMiner.mine("data.pdf");
    }
    
    private static void demonstrateJSONMining() {
        System.out.println("=== JSON Data Mining ===");
        DataMiner jsonMiner = new JSONDataMiner();
        jsonMiner.mine("data.json");
    }
}

// Additional utility class for monitoring execution
class DataMiningMonitor {
    private final List<String> executionSteps = new ArrayList<>();
    
    public void logStep(String step) {
        executionSteps.add(step);
        System.out.println("Executing: " + step);
    }
    
    public void printExecutionSummary() {
        System.out.println("\nExecution Summary:");
        for (int i = 0; i < executionSteps.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, executionSteps.get(i));
        }
    }
}
