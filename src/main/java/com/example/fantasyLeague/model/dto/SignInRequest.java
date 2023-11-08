package com.example.fantasyLeague.model.dto;

import com.example.fantasyLeague.security.constraints.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignInRequest {

    @NotEmpty
    @Size(min = 2, message = "username must be 2 characters or more")
    private String username;

    @NotEmpty
    @ValidPassword
    private String password;

    public SignInRequest() {
    }

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
