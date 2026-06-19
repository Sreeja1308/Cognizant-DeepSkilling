package StrategyPatternExample;

public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;
    
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        System.out.println("PayPal Account: " + email);
        System.out.println("✓ PayPal payment successful!");
    }
    
    @Override
    public String getMethodName() {
        return "PayPal";
    }
}