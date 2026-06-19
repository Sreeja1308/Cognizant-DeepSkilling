package ObserverPatternExample;

import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockName;
    private double price;
    
    public StockMarket(String stockName) {
        this.stockName = stockName;
        this.observers = new ArrayList<>();
        this.price = 0.0;
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println(observer.getName() + " registered for " + stockName);
    }
    
    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println(observer.getName() + " deregistered from " + stockName);
    }
    
    @Override
    public void notifyObservers() {
        System.out.println("\n--- Notifying " + observers.size() + " observers ---");
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }
    
    public void setPrice(double newPrice) {
        System.out.println("\n" + stockName + " price changed: $" + price + " → $" + newPrice);
        this.price = newPrice;
        notifyObservers();
    }
    
    public String getStockName() {
        return stockName;
    }
    
    public double getPrice() {
        return price;
    }
}