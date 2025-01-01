import java.util.ArrayList;
import java.util.List;

// Component interface
interface FileSystemComponent {
    void showDetails(String indent);
    long getSize();
    String getName();
}

// Leaf class representing a file
class File implements FileSystemComponent {
    private final String name;
    private final long size;
    
    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }
    
    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📄 " + name + " (" + size + " bytes)");
    }
    
    @Override
    public long getSize() {
        return size;
    }
    
    @Override
    public String getName() {
        return name;
    }
}

// Composite class representing a directory
class Directory implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> components;
    
    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }
    
    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }
    
    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }
    
    public List<FileSystemComponent> getComponents() {
        return new ArrayList<>(components);
    }
    
    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📁 " + name + " (" + getSize() + " bytes)");
        for (FileSystemComponent component : components) {
            component.showDetails(indent + "    ");
        }
    }
    
    @Override
    public long getSize() {
        return components.stream()
                        .mapToLong(FileSystemComponent::getSize)
                        .sum();
    }
    
    @Override
    public String getName() {
        return name;
    }
}

// Search functionality using visitor-like approach
class FileSystemSearcher {
    public List<FileSystemComponent> searchByName(FileSystemComponent root, String searchTerm) {
        List<FileSystemComponent> results = new ArrayList<>();
        searchRecursive(root, searchTerm.toLowerCase(), results);
        return results;
    }
    
    private void searchRecursive(FileSystemComponent component, String searchTerm, 
                               List<FileSystemComponent> results) {
        if (component.getName().toLowerCase().contains(searchTerm)) {
            results.add(component);
        }
        
        if (component instanceof Directory directory) {
            for (FileSystemComponent child : directory.getComponents()) {
                searchRecursive(child, searchTerm, results);
            }
        }
    }
}

// Main class to demonstrate the composite pattern
public class Main {
    public static void main(String[] args) {
        // Create the root directory
        Directory root = new Directory("root");
        
        // Create some subdirectories
        Directory home = new Directory("home");
        Directory documents = new Directory("documents");
        Directory pictures = new Directory("pictures");
        
        // Create some files
        File file1 = new File("document1.txt", 1000);
        File file2 = new File("document2.pdf", 2000);
        File picture1 = new File("vacation.jpg", 3000);
        File picture2 = new File("family.jpg", 4000);
        File config = new File("config.xml", 500);
        
        // Build the directory structure
        root.addComponent(home);
        root.addComponent(config);
        
        home.addComponent(documents);
        home.addComponent(pictures);
        
        documents.addComponent(file1);
        documents.addComponent(file2);
        
        pictures.addComponent(picture1);
        pictures.addComponent(picture2);
        
        // Show the entire directory structure
        System.out.println("File System Structure:");
        System.out.println("=====================");
        root.showDetails("");
        
        // Demonstrate searching
        System.out.println("\nSearch Results:");
        System.out.println("==============");
        FileSystemSearcher searcher = new FileSystemSearcher();
        
        // Search for files containing "doc"
        System.out.println("\nSearching for 'doc':");
        List<FileSystemComponent> docResults = searcher.searchByName(root, "doc");
        docResults.forEach(result -> result.showDetails(""));
        
        // Search for files containing "jpg"
        System.out.println("\nSearching for 'jpg':");
        List<FileSystemComponent> jpgResults = searcher.searchByName(root, "jpg");
        jpgResults.forEach(result -> result.showDetails(""));
        
        // Calculate and display total size of different components
        System.out.println("\nSize Information:");
        System.out.println("================");
        System.out.println("Total size of root: " + root.getSize() + " bytes");
        System.out.println("Size of documents directory: " + documents.getSize() + " bytes");
        System.out.println("Size of pictures directory: " + pictures.getSize() + " bytes");
    }
}

// Additional utility to demonstrate file system operations
class FileSystemOperations {
    public static void copyDirectory(Directory source, Directory destination) {
        for (FileSystemComponent component : source.getComponents()) {
            if (component instanceof File file) {
                // Create a new file with the same properties
                destination.addComponent(new File(file.getName(), file.getSize()));
            } else if (component instanceof Directory dir) {
                // Create a new directory and recursively copy contents
                Directory newDir = new Directory(dir.getName());
                destination.addComponent(newDir);
                copyDirectory(dir, newDir);
            }
        }
    }
    
    public static void printDirectoryStatistics(Directory directory) {
        int totalFiles = 0;
        int totalDirectories = 0;
        long totalSize = 0;
        
        for (FileSystemComponent component : directory.getComponents()) {
            if (component instanceof File) {
                totalFiles++;
                totalSize += component.getSize();
            } else if (component instanceof Directory dir) {
                totalDirectories++;
                totalSize += dir.getSize();
            }
        }
        
        System.out.println("\nDirectory Statistics for: " + directory.getName());
        System.out.println("Total Files: " + totalFiles);
        System.out.println("Total Directories: " + totalDirectories);
        System.out.println("Total Size: " + totalSize + " bytes");
    }
}
