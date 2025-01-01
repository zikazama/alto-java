// Subsystem components
class TV {
    public void turnOn() {
        System.out.println("TV is turning on");
    }
    
    public void turnOff() {
        System.out.println("TV is turning off");
    }
    
    public void setInput(String input) {
        System.out.println("Setting TV input to: " + input);
    }
}

class SoundSystem {
    public void turnOn() {
        System.out.println("Sound system is turning on");
    }
    
    public void turnOff() {
        System.out.println("Sound system is turning off");
    }
    
    public void setVolume(int volume) {
        System.out.println("Setting volume to: " + volume);
    }
    
    public void setSurroundSound(boolean enable) {
        System.out.println("Setting surround sound: " + enable);
    }
}

class StreamingDevice {
    public void turnOn() {
        System.out.println("Streaming device is turning on");
    }
    
    public void turnOff() {
        System.out.println("Streaming device is turning off");
    }
    
    public void startApp(String app) {
        System.out.println("Starting " + app + " app");
    }
}

class Lights {
    public void dim(int percentage) {
        System.out.println("Dimming lights to " + percentage + "%");
    }
    
    public void brighten() {
        System.out.println("Brightening lights to 100%");
    }
}

// Facade
class HomeTheaterFacade {
    private final TV tv;
    private final SoundSystem soundSystem;
    private final StreamingDevice streamingDevice;
    private final Lights lights;
    
    public HomeTheaterFacade(TV tv, SoundSystem soundSystem, 
                            StreamingDevice streamingDevice, Lights lights) {
        this.tv = tv;
        this.soundSystem = soundSystem;
        this.streamingDevice = streamingDevice;
        this.lights = lights;
    }
    
    public void watchMovie(String movie) {
        System.out.println("=== Starting movie mode for: " + movie + " ===");
        lights.dim(20);
        tv.turnOn();
        tv.setInput("HDMI1");
        soundSystem.turnOn();
        soundSystem.setSurroundSound(true);
        soundSystem.setVolume(50);
        streamingDevice.turnOn();
        streamingDevice.startApp("Netflix");
        System.out.println("=== Ready to watch " + movie + " ===");
    }
    
    public void endMovie() {
        System.out.println("=== Ending movie mode ===");
        streamingDevice.turnOff();
        soundSystem.turnOff();
        tv.turnOff();
        lights.brighten();
        System.out.println("=== System shut down complete ===");
    }
    
    public void listenToMusic() {
        System.out.println("=== Starting music mode ===");
        lights.dim(50);
        soundSystem.turnOn();
        soundSystem.setSurroundSound(false);
        soundSystem.setVolume(40);
        streamingDevice.turnOn();
        streamingDevice.startApp("Spotify");
        System.out.println("=== Ready for music ===");
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create subsystem components
        TV tv = new TV();
        SoundSystem soundSystem = new SoundSystem();
        StreamingDevice streamingDevice = new StreamingDevice();
        Lights lights = new Lights();
        
        // Create the facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, soundSystem, 
                                                             streamingDevice, lights);
        
        // Use the facade
        homeTheater.watchMovie("The Matrix");
        System.out.println("\n--- Movie finished ---\n");
        homeTheater.endMovie();
        System.out.println("\n--- Switching to music ---\n");
        homeTheater.listenToMusic();
    }
}
