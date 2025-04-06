package com.pulseofthepeople.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.DETACH })
    @JoinTable(
            name = "question_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;


    public void addQuestion(Question newQuestion){
        if(questions == null){
            questions = new ArrayList<Question>();
        }

        questions.add(newQuestion);
    }

    public Tag(){}

    public Tag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
