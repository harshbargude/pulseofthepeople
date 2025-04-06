package com.pulseofthepeople.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class QuestionForm {

    @NotBlank(message = "Title is required..")
    @Size(min=3 ,message = "Min 3 characters are required")
    private String title;

    @NotBlank(message = "Details are required..")
    @Size(min=5 ,message = "Min 5 characters are required")
    private String details;

    private String region;

    private String official;

    private String tag;

    private LocalDateTime createdAt = LocalDateTime.now();

    public QuestionForm(){

    }

    public QuestionForm(String title, String details, String region, String official, String tag, LocalDateTime createdAt) {
        this.title = title;
        this.details = details;
        this.region = region;
        this.official = official;
        this.tag = tag;
        this.createdAt = createdAt;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
