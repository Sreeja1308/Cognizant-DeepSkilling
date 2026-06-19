package ObserverPatternExample;

public class MobileApp implements Observer {
    private String name;
    
    public MobileApp(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String stockName, double price) {
        System.out.println("📱 " + name + " (MobileApp): " + stockName + " is now $" + price);
    }
    
    @Override
    public String getName() {
        return name;
    }
}