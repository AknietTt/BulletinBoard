package com.example.BulletinBoard.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(nullable = false, name = "data")
    private byte[] data;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bulletinBoard_id", referencedColumnName = "id")
    private BulletinBoard bulletinBoard;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

;

    public BulletinBoard getBulletinBoard() {
        return bulletinBoard;
    }

    public void setBulletinBoard(BulletinBoard bulletinBoard) {
        this.bulletinBoard = bulletinBoard;
    }

    public Image() {}

    public Image(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
