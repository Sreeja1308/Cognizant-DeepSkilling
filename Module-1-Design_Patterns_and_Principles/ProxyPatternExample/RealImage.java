package ProxyPatternExample;

public class RealImage implements Image {
    private String fileName;
    
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromServer();
    }
    
    private void loadFromServer() {
        System.out.println("Loading image from remote server: " + fileName);
       
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
    
    @Override
    public String getFileName() {
        return fileName;
    }
}