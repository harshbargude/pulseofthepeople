package com.pulseofthepeople.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {

    @NotBlank(message = "User name is required..")
    @Size(min=3 ,message = "Min 3 characters are required")
    private String firstName;

    @NotBlank(message = "User name is required..")
    @Size(min=3 ,message = "Min 3 characters are required")
    private String lastName;

    @Email(message = "Invalid email address")
    @NotBlank(message ="Email required.")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6 ,message = "Min 6 characters are required in password!")
    private String password;

    public @NotBlank(message = "User name is required..") @Size(min = 3, message = "Min 3 characters are required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "User name is required..") @Size(min = 3, message = "Min 3 characters are required") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "User name is required..") @Size(min = 3, message = "Min 3 characters are required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "User name is required..") @Size(min = 3, message = "Min 3 characters are required") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "Invalid email address") @NotBlank(message = "Email required.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email address") @NotBlank(message = "Email required.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 6, message = "Min 6 characters are required in password!") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 6, message = "Min 6 characters are required in password!") String password) {
        this.password = password;
    }
}
