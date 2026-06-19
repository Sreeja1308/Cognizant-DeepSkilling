package BuilderPatternExample;

public class TestBuilder {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo ===\n");
        
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB")
                .build();
        System.out.println("Basic Computer:");
        System.out.println(basicComputer);
        
        System.out.println();
        
        Computer gamingComputer = new Computer.Builder("Intel i9", "32GB")
                .storage("1TB NVMe SSD")
                .graphicsCard("NVIDIA RTX 4080")
                .motherboard("Gaming Motherboard")
                .bluetooth(true)
                .wifi(true)
                .build();
        System.out.println("Gaming Computer:");
        System.out.println(gamingComputer);
        
        System.out.println();
        
        Computer workstation = new Computer.Builder("AMD Ryzen 9", "64GB")
                .storage("2TB SSD")
                .graphicsCard("NVIDIA Quadro")
                .motherboard("Workstation Motherboard")
                .bluetooth(true)
                .build();
        System.out.println("Workstation:");
        System.out.println(workstation);
    }
}