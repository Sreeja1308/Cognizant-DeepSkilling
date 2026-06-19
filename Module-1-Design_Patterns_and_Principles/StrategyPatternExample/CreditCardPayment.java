package StrategyPatternExample;
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expiryDate;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
        System.out.println("Card Number: **** **** **** " + cardNumber.substring(12));
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("✓ Credit Card payment successful!");
    }
    
    @Override
    public String getMethodName() {
        return "Credit Card";
    }
}