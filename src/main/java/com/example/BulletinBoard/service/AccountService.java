package com.example.BulletinBoard.service;

import com.example.BulletinBoard.model.Account;

import com.example.BulletinBoard.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void save(Account user) {
        enrichUser(user);
        accountRepository.save(user);
    }
    @Transactional
    public void deleteById(int id){
        accountRepository.deleteById(id);
    }
    public Account getAccountById(int id){
        return accountRepository.getAccountById(id);
    }
    public boolean isAccount(String email){
       return accountRepository.existsByEmail(email);
    }
    public Account findAccountByEmail(String email){
        return accountRepository.findByEmail(email);
    }
    @Transactional
    public void updateIsActiveByEmail(String email, boolean isActive){
        accountRepository.updateIsActiveByEmail(email,isActive);
    }
    private void enrichUser(Account user){
        user.setActivate(false);
    }
}
