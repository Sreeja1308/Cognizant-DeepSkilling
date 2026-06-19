package FactoryMethodPatternExample;

public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel spreadsheet...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Excel spreadsheet...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Excel spreadsheet...");
    }
    
    @Override
    public String getType() {
        return "Microsoft Excel Spreadsheet";
    }
}