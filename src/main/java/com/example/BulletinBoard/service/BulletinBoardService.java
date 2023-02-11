package com.example.BulletinBoard.service;
import com.example.BulletinBoard.model.BulletinBoard;
import com.example.BulletinBoard.model.MaxPrice;
import com.example.BulletinBoard.repository.AccountRepository;
import com.example.BulletinBoard.repository.BulletinBoardRepository;
import com.example.BulletinBoard.repository.MaxPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class BulletinBoardService {
    private BulletinBoardRepository bulletinBoardRepository;
    private AccountRepository accountRepository;
    private MaxPriceRepository maxPriceRepository;
    @Autowired
    public BulletinBoardService(BulletinBoardRepository bulletinBoardRepository, AccountRepository accountRepository, MaxPriceRepository maxPriceRepository) {
        this.bulletinBoardRepository = bulletinBoardRepository;
        this.accountRepository = accountRepository;
        this.maxPriceRepository = maxPriceRepository;
    }
    @Transactional
    public void save(BulletinBoard bulletinBoard, int id) {
        enrichBulletinBoard(bulletinBoard,id);
        bulletinBoardRepository.save(bulletinBoard);
    }

    public List<BulletinBoard> findAll() {
        return  bulletinBoardRepository.findAll();
    }
    @Transactional
    public void raisePrice(MaxPrice maxPrice, int buyerId,int sellerId) {
        if(bulletinBoardRepository.findByAccount_Id(sellerId).getMaxPrice().getMaxPrice() >= maxPrice.getMaxPrice()
                || bulletinBoardRepository.findByAccount_Id(sellerId).getMinPrice() >= maxPrice.getMaxPrice()){

            return ;
        }
        maxPrice.setAccount(accountRepository.findById(buyerId));
        String emailSeller = bulletinBoardRepository.findByAccount_Id(sellerId).getAccount().getEmail();
        String emailBayer =  maxPriceRepository.findByAccount_Id(buyerId).getAccount().getEmail();

        bulletinBoardRepository.findByAccount_Id(buyerId).setMaxPrice(maxPrice);
    }

    @Transactional
    public void delete(int bulletinId) {
        bulletinBoardRepository.deleteById(bulletinId);
    }
    private void enrichBulletinBoard(BulletinBoard bulletinBoard, int id) {
        bulletinBoard.setAccount(accountRepository.findById(id));
    }



}
