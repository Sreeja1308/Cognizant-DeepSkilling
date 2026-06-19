package SingletonPatternExample;

public class Logger {
    private static volatile Logger instance;

    private Logger() {
       
        System.out.println("Logger instance created!");
    }
    
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    
    public void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}