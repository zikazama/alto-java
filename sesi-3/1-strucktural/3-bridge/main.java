// Implementation interface
interface Device {
    void powerOn();
    void powerOff();
    void setVolume(int percent);
    void setChannel(int channel);
    boolean isEnabled();
    int getVolume();
    int getChannel();
}

// Concrete Implementations
class TV implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;
    
    @Override
    public void powerOn() {
        on = true;
        System.out.println("TV is powered on");
    }
    
    @Override
    public void powerOff() {
        on = false;
        System.out.println("TV is powered off");
    }
    
    @Override
    public void setVolume(int percent) {
        if (percent >= 0 && percent <= 100) {
            this.volume = percent;
            System.out.println("TV volume set to: " + percent);
        }
    }
    
    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("TV channel set to: " + channel);
    }
    
    @Override
    public boolean isEnabled() {
        return on;
    }
    
    @Override
    public int getVolume() {
        return volume;
    }
    
    @Override
    public int getChannel() {
        return channel;
    }
}

class Radio implements Device {
    private boolean on = false;
    private int volume = 20;
    private int channel = 88;
    
    @Override
    public void powerOn() {
        on = true;
        System.out.println("Radio is powered on");
    }
    
    @Override
    public void powerOff() {
        on = false;
        System.out.println("Radio is powered off");
    }
    
    @Override
    public void setVolume(int percent) {
        if (percent >= 0 && percent <= 100) {
            this.volume = percent;
            System.out.println("Radio volume set to: " + percent);
        }
    }
    
    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Radio frequency set to: " + channel + " MHz");
    }
    
    @Override
    public boolean isEnabled() {
        return on;
    }
    
    @Override
    public int getVolume() {
        return volume;
    }
    
    @Override
    public int getChannel() {
        return channel;
    }
}

// Abstraction
abstract class RemoteControl {
    protected Device device;
    
    protected RemoteControl(Device device) {
        this.device = device;
    }
    
    abstract void togglePower();
    abstract void volumeUp();
    abstract void volumeDown();
    abstract void channelUp();
    abstract void channelDown();
}

// Refined Abstraction
class BasicRemoteControl extends RemoteControl {
    public BasicRemoteControl(Device device) {
        super(device);
    }
    
    @Override
    void togglePower() {
        if (device.isEnabled()) {
            device.powerOff();
        } else {
            device.powerOn();
        }
    }
    
    @Override
    void volumeUp() {
        device.setVolume(Math.min(device.getVolume() + 10, 100));
    }
    
    @Override
    void volumeDown() {
        device.setVolume(Math.max(device.getVolume() - 10, 0));
    }
    
    @Override
    void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
    
    @Override
    void channelDown() {
        device.setChannel(Math.max(device.getChannel() - 1, 1));
    }
}

class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }
    
    @Override
    void togglePower() {
        if (device.isEnabled()) {
            device.powerOff();
        } else {
            device.powerOn();
        }
    }
    
    @Override
    void volumeUp() {
        device.setVolume(Math.min(device.getVolume() + 5, 100));
    }
    
    @Override
    void volumeDown() {
        device.setVolume(Math.max(device.getVolume() - 5, 0));
    }
    
    @Override
    void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
    
    @Override
    void channelDown() {
        device.setChannel(Math.max(device.getChannel() - 1, 1));
    }
    
    // Additional advanced features
    void mute() {
        device.setVolume(0);
        System.out.println("Muted");
    }
    
    void jumpToChannel(int channel) {
        device.setChannel(channel);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Test with TV
        Device tv = new TV();
        RemoteControl basicTvRemote = new BasicRemoteControl(tv);
        AdvancedRemoteControl advancedTvRemote = new AdvancedRemoteControl(tv);
        
        System.out.println("Testing Basic TV Remote:");
        basicTvRemote.togglePower();
        basicTvRemote.volumeUp();
        basicTvRemote.channelUp();
        
        System.out.println("\nTesting Advanced TV Remote:");
        advancedTvRemote.togglePower();
        advancedTvRemote.volumeUp();
        advancedTvRemote.mute();
        advancedTvRemote.jumpToChannel(45);
        
        // Test with Radio
        System.out.println("\nTesting Basic Radio Remote:");
        Device radio = new Radio();
        RemoteControl basicRadioRemote = new BasicRemoteControl(radio);
        basicRadioRemote.togglePower();
        basicRadioRemote.volumeUp();
        basicRadioRemote.channelUp();
        
        System.out.println("\nTesting Advanced Radio Remote:");
        AdvancedRemoteControl advancedRadioRemote = new AdvancedRemoteControl(radio);
        advancedRadioRemote.togglePower();
        advancedRadioRemote.volumeUp();
        advancedRadioRemote.jumpToChannel(101);
    }
}
