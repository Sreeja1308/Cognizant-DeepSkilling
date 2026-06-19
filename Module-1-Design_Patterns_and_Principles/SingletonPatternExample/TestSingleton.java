package SingletonPatternExample;

public class TestSingleton {
    public static void main(String[] args) {
        Logger l1 = Logger.getInstance();
        Logger l2 = Logger.getInstance();
        
        l1.log("Application started");
        l2.log("User logged in");
        
        System.out.println("\n--- Singleton Verification ---");
        System.out.println("l1 hashcode: " + l1.hashCode());
        System.out.println("l2 hashcode: " + l2.hashCode());
        
        if (l1 == l2) {
            System.out.println("✓ Both references point to the SAME instance!");
        } else {
            System.out.println("✗ Different instances exist!");
        }
    }
}