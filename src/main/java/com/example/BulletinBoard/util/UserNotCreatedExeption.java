package com.example.BulletinBoard.util;

public class UserNotCreatedExeption extends RuntimeException {
    public UserNotCreatedExeption(String msg) {
        super(msg);
    }
}
