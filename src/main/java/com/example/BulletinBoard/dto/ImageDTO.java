package com.example.BulletinBoard.dto;

import com.example.BulletinBoard.model.BulletinBoard;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ImageDTO {
    @Lob
    @Column(nullable = false, name = "data")
    private byte[] data;
    @NotBlank
    private BulletinBoard bulletinBoard;
    @NotNull
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public BulletinBoard getBulletinBoard() {
        return bulletinBoard;
    }

    public void setBulletinBoard(BulletinBoard bulletinBoard) {
        this.bulletinBoard = bulletinBoard;
    }
}
