package com.example.fantasyLeague.dto;

import com.example.fantasyLeague.security.constraints.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty
    @Size(min = 2, message = "must be 8 or more characters in length")
    private String username;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    @ValidPassword
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
