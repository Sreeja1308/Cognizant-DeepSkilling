package ProxyPatternExample;

import java.util.HashMap;
import java.util.Map;

public class ProxyImage implements Image {
    private String fileName;
    private RealImage realImage;
    private static Map<String, RealImage> cache = new HashMap<>();
    
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void display() {
        if (realImage == null) {
            if (cache.containsKey(fileName)) {
                System.out.println("Retrieving from cache: " + fileName);
                realImage = cache.get(fileName);
            } else {
                realImage = new RealImage(fileName);
                cache.put(fileName, realImage);
            }
        }
        realImage.display();
    }
    
    @Override
    public String getFileName() {
        return fileName;
    }
}