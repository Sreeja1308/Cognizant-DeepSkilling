package com.example.ormlearn.entity;

import javax.persistence.*;
import java.util.List;  // ← Add this import

@Entity
@Table(name = "question")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private int id;
    
    @Column(name = "qu_text")
    private String text;
    
    @Column(name = "qu_score")
    private double score;
    
    @OneToMany(mappedBy = "question")
    private List<Options> options;
    
    @OneToMany(mappedBy = "question")
    private List<AttemptQuestion> attemptQuestions;
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    
    public List<Options> getOptions() { return options; }
    public void setOptions(List<Options> options) { this.options = options; }
    
    public List<AttemptQuestion> getAttemptQuestions() { return attemptQuestions; }
    public void setAttemptQuestions(List<AttemptQuestion> attemptQuestions) { 
        this.attemptQuestions = attemptQuestions; 
    }
}