package BuilderPatternExample;

public class Computer {
    private final String cpu;
    private final String ram;
    
    private final String storage;
    private final String graphicsCard;
    private final String motherboard;
    private final boolean bluetooth;
    private final boolean wifi;
    
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.motherboard = builder.motherboard;
        this.bluetooth = builder.bluetooth;
        this.wifi = builder.wifi;
    }
    
    public static class Builder {
        private final String cpu;
        private final String ram;
        
        private String storage = "256GB SSD";
        private String graphicsCard = "Integrated";
        private String motherboard = "Standard";
        private boolean bluetooth = false;
        private boolean wifi = true;
        
        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }
        
        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }
        
        public Builder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }
        
        public Builder bluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }
        
        public Builder wifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
    
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGraphicsCard() { return graphicsCard; }
    public String getMotherboard() { return motherboard; }
    public boolean hasBluetooth() { return bluetooth; }
    public boolean hasWifi() { return wifi; }
    
    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", motherboard='" + motherboard + '\'' +
                ", bluetooth=" + bluetooth +
                ", wifi=" + wifi +
                '}';
    }
}