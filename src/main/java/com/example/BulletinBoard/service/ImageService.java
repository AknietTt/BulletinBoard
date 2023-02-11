package com.example.BulletinBoard.service;

import com.example.BulletinBoard.model.Account;
import com.example.BulletinBoard.model.BulletinBoard;
import com.example.BulletinBoard.model.Image;
import com.example.BulletinBoard.repository.AccountRepository;
import com.example.BulletinBoard.repository.BulletinBoardRepository;
import com.example.BulletinBoard.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ImageService {
    private ImageRepository imageRepository;
    private AccountRepository accountRepository;
    private BulletinBoardRepository bulletinBoardRepository;
    @Autowired
    public ImageService(ImageRepository imageRepository, AccountRepository accountRepository, BulletinBoardRepository bulletinBoardRepository) {
        this.imageRepository = imageRepository;
        this.accountRepository = accountRepository;
        this.bulletinBoardRepository = bulletinBoardRepository;
    }
    @Transactional
    public void save(Image convertToImage, int bullitenId, int userid) {
        enrichIamge(convertToImage, bullitenId,userid);
        imageRepository.save(convertToImage);
    }

    private void enrichIamge(Image image, int bullitenId, int userid){
        Account account = accountRepository.getAccountById(userid);
        BulletinBoard bulletinBoard= bulletinBoardRepository.getById(bullitenId);
        image.setAccount(account);
        image.setBulletinBoard(bulletinBoard);
    }
}
