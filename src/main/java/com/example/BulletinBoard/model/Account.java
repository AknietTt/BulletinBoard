package com.example.BulletinBoard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Email
    @NotEmpty
    @Column(name = "email",unique = true)
    private String email;

    @NotEmpty
    @Size(min = 6,max = 3000)
    @Column(name = "password")
    private String password;


    @NotEmpty
    @Size(min = 3,max = 100)
    @Column(name ="username")
    private String username;

    @Column(name ="isActivate")
    private boolean isActivate;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BulletinBoard> bulletinBoards;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MaxPrice> maxPrices;
    public Account() {
    }

    public Account(String email, String password, String username, List<BulletinBoard> bulletinBoards) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.bulletinBoards = bulletinBoards;
    }

    public List<BulletinBoard> getBulletinBoards() {
        return bulletinBoards;
    }

    public void setBulletinBoards(List<BulletinBoard> bulletinBoards) {
        this.bulletinBoards = bulletinBoards;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }
}
