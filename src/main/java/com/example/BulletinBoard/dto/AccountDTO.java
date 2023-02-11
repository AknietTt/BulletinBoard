package com.example.BulletinBoard.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public class AccountDTO {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 6,max = 3000)
    private String password;
    @NotEmpty
    @Size(min = 6,max = 3000)
    private String repeatPassword;
    @NotEmpty
    @Size(min = 3,max = 100)
    private String username;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
