package FactoryMethodPatternExample;

public class TestFactoryMethod {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");
        
        // Create Word document
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        System.out.println("Created: " + wordDoc.getType());
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();
        
        System.out.println();
        
        // Create PDF document
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        System.out.println("Created: " + pdfDoc.getType());
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();
        
        System.out.println();
        
        // Create Excel document
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        System.out.println("Created: " + excelDoc.getType());
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();
    }
}