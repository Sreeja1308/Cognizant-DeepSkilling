package com.example.ormlearn.entity;

import javax.persistence.*;

@Entity
@Table(name = "attempt_option")
public class AttemptOption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ao_id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ao_aq_id")
    private AttemptQuestion attemptQuestion;
    
    @ManyToOne
    @JoinColumn(name = "ao_op_id")
    private Options options;
    
    @Column(name = "ao_selected")
    private boolean selected;
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public AttemptQuestion getAttemptQuestion() { return attemptQuestion; }
    public void setAttemptQuestion(AttemptQuestion attemptQuestion) { 
        this.attemptQuestion = attemptQuestion; 
    }
    
    public Options getOptions() { return options; }
    public void setOptions(Options options) { this.options = options; }
    
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}