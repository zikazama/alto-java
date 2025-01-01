// Subject interface
interface Document {
    void view();
    void edit(String newContent);
}

// Real Subject
class RealDocument implements Document {
    private String content;
    private String title;
    
    public RealDocument(String title, String content) {
        this.title = title;
        this.content = content;
        loadDocumentFromStorage(); // Simulate loading document
    }
    
    private void loadDocumentFromStorage() {
        System.out.println("Loading document '" + title + "' from storage...");
        // Simulate expensive loading operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void view() {
        System.out.println("Displaying document content: " + content);
    }
    
    @Override
    public void edit(String newContent) {
        System.out.println("Modifying document content");
        this.content = newContent;
    }
}

// User class to represent system users
record User(String name, String role) {}

// Protection Proxy
class SecureDocumentProxy implements Document {
    private RealDocument realDocument;
    private final String title;
    private final String content;
    private final User user;
    
    public SecureDocumentProxy(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
    
    private boolean hasViewPermission() {
        // All users can view documents
        return true;
    }
    
    private boolean hasEditPermission() {
        // Only users with role 'ADMIN' or 'EDITOR' can edit
        return user.role().equals("ADMIN") || user.role().equals("EDITOR");
    }
    
    private void initializeDocumentIfNeeded() {
        if (realDocument == null) {
            realDocument = new RealDocument(title, content);
        }
    }
    
    @Override
    public void view() {
        if (hasViewPermission()) {
            System.out.println("User '" + user.name() + "' accessing document...");
            initializeDocumentIfNeeded();
            realDocument.view();
        } else {
            System.out.println("Access Denied: User '" + user.name() + 
                             "' does not have view permissions.");
        }
    }
    
    @Override
    public void edit(String newContent) {
        if (hasEditPermission()) {
            System.out.println("User '" + user.name() + "' editing document...");
            initializeDocumentIfNeeded();
            realDocument.edit(newContent);
        } else {
            System.out.println("Access Denied: User '" + user.name() + 
                             "' does not have edit permissions.");
        }
    }
}

// Virtual Proxy (Lazy Loading) for large documents
class LazyLoadingDocumentProxy implements Document {
    private RealDocument realDocument;
    private final String title;
    private final String content;
    
    public LazyLoadingDocumentProxy(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    private void initializeDocumentIfNeeded() {
        if (realDocument == null) {
            realDocument = new RealDocument(title, content);
        }
    }
    
    @Override
    public void view() {
        initializeDocumentIfNeeded();
        realDocument.view();
    }
    
    @Override
    public void edit(String newContent) {
        initializeDocumentIfNeeded();
        realDocument.edit(newContent);
    }
}

// Logging Proxy for audit trails
class LoggingDocumentProxy implements Document {
    private final Document document;
    private final String documentName;
    
    public LoggingDocumentProxy(Document document, String documentName) {
        this.document = document;
        this.documentName = documentName;
    }
    
    private void logOperation(String operation) {
        System.out.println("LOG: " + operation + " operation performed on document '" + 
                         documentName + "' at " + java.time.LocalDateTime.now());
    }
    
    @Override
    public void view() {
        logOperation("VIEW");
        document.view();
    }
    
    @Override
    public void edit(String newContent) {
        logOperation("EDIT");
        document.edit(newContent);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create users with different roles
        User adminUser = new User("Alice", "ADMIN");
        User editorUser = new User("Bob", "EDITOR");
        User viewerUser = new User("Charlie", "VIEWER");
        
        // Create document with protection proxy
        System.out.println("=== Testing Protection Proxy ===");
        Document secureDoc1 = new SecureDocumentProxy(
            "Confidential Report",
            "This is a confidential report content.",
            adminUser
        );
        
        Document secureDoc2 = new SecureDocumentProxy(
            "Confidential Report",
            "This is a confidential report content.",
            viewerUser
        );
        
        // Test access with different users
        System.out.println("\nAdmin user accessing document:");
        secureDoc1.view();
        secureDoc1.edit("Updated content by admin");
        
        System.out.println("\nViewer user accessing document:");
        secureDoc2.view();
        secureDoc2.edit("Attempt to edit by viewer"); // This will be denied
        
        // Test lazy loading proxy
        System.out.println("\n=== Testing Lazy Loading Proxy ===");
        Document lazyDoc = new LazyLoadingDocumentProxy(
            "Large Document",
            "This is a large document that should be loaded lazily."
        );
        System.out.println("Document proxy created, but document not loaded yet...");
        System.out.println("Accessing document for the first time:");
        lazyDoc.view();
        
        // Test logging proxy
        System.out.println("\n=== Testing Logging Proxy ===");
        Document loggingDoc = new LoggingDocumentProxy(
            new RealDocument("Logged Document", "Content that needs to be audited"),
            "Logged Document"
        );
        loggingDoc.view();
        loggingDoc.edit("New content with audit trail");
    }
}
