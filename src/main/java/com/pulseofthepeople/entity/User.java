package com.pulseofthepeople.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@ToString
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @jakarta.persistence.Column(name = "first_name")
    private String firstName;

    @jakarta.persistence.Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    private String party;

    private String profileImage;

    private String bio;

    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Question> questions;

//    default constructor
    public User(){

    }

    public User(String firstName, String lastName, String email, String password, String party, String profileImage, String bio, boolean enabled, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.party = party;
        this.profileImage = profileImage;
        this.bio = bio;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

/*

    user_id: Integer (Primary Key)
    username: String
    email: String
    password_hash: String
    full_name: String
    role: Enum (CITIZEN, MLA, JOURNALIST, ADMIN)
    region: String
    party: String (for MLAs)
    profile_image: String (URL)
    bio: Text
    created_at: Timestamp
    last_login: Timestamp
    is_active: Boolean

     */

}
