package com.example.ormlearn.dto;

public class AttemptDetailDTO {
    private String username;
    private String attemptedDate;
    private String questionText;
    private String optionText;
    private double questionScore;
    private boolean isCorrect;
    private boolean isSelected;
    
    // Constructor
    public AttemptDetailDTO(String username, String attemptedDate, String questionText, 
                           String optionText, double questionScore, boolean isCorrect, 
                           boolean isSelected) {
        this.username = username;
        this.attemptedDate = attemptedDate;
        this.questionText = questionText;
        this.optionText = optionText;
        this.questionScore = questionScore;
        this.isCorrect = isCorrect;
        this.isSelected = isSelected;
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getAttemptedDate() { return attemptedDate; }
    public void setAttemptedDate(String attemptedDate) { this.attemptedDate = attemptedDate; }
    
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    
    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }
    
    public double getQuestionScore() { return questionScore; }
    public void setQuestionScore(double questionScore) { this.questionScore = questionScore; }
    
    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }
    
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}