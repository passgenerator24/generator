package com.example.demo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CRUD {
    private String credential_id;
    private String username;
    private String password;
    private LocalDateTime dateCreated;

    public String getUsername() {
        return username;
    }
}
