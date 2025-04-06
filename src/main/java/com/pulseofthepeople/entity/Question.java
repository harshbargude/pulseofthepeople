package com.pulseofthepeople.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "details")
    private String details;

    @Column(name = "region")
    private String region;

    @Column(name = "official")
    private String official;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Vote> vote;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,CascadeType.MERGE,
                    CascadeType.REFRESH,CascadeType.DETACH })
    @JoinTable(
            name = "question_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    /*

    question_id: Integer (Primary Key)
    title: String
    details: Text
    user_id: Integer (Foreign Key → User)
    region: String
    party_tag: String (optional)
    mla_id: Integer (Foreign Key → User, optional)
    created_at: Timestamp
    updated_at: Timestamp
    status: Enum (OPEN, ANSWERED, CLOSED)
    is_featured: Boolean
    moderation_status: Enum (PENDING, APPROVED, REJECTED)

     */

    public Question(){
    }

    public Question(Long id, String title, String details, String region, String official, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.region = region;
        this.official = official;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", region='" + region + '\'' +
                ", official='" + official + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
 