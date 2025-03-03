package com.zambeyzz.MovieAPIDevelopment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private String role;
}
