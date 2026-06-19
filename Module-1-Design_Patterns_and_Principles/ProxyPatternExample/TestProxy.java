package ProxyPatternExample;

public class TestProxy {
    public static void main(String[] args) {
       
        System.out.println("First Access (Loading from server) ");
        Image image1 = new ProxyImage("photo1.jpg");
        image1.display();
        
        System.out.println("\n Second Access (From cache)");
        Image image2 = new ProxyImage("photo1.jpg");
        image2.display();
        
        System.out.println("\nDifferent Image (Loading from server)");
        Image image3 = new ProxyImage("photo2.jpg");
        image3.display();
        
        System.out.println("\nFirst Image Again (From cache)");
        image1.display();
        
        System.out.println("\nVerification");
        System.out.println("Total unique images loaded: 2");
    }
}