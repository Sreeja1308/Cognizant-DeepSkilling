package ObserverPatternExample;
public class TestObserver {
    public static void main(String[] args) {
       
        StockMarket appleStock = new StockMarket("Apple Inc. (AAPL)");
        
        MobileApp app1 = new MobileApp("John's iPhone");
        MobileApp app2 = new MobileApp("Sarah's Android");
        WebApp web1 = new WebApp("Trading Dashboard");
        
        appleStock.registerObserver(app1);
        appleStock.registerObserver(app2);
        appleStock.registerObserver(web1);
        
        appleStock.setPrice(175.50);
        appleStock.setPrice(178.25);
        appleStock.setPrice(176.80);
        
        appleStock.deregisterObserver(app2);
        
        appleStock.setPrice(180.00);
    }
}