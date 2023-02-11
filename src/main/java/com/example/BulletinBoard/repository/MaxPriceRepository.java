package com.example.BulletinBoard.repository;

import com.example.BulletinBoard.model.Account;
import com.example.BulletinBoard.model.MaxPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaxPriceRepository extends JpaRepository<MaxPrice,Integer> {
    MaxPrice findByAccount_Id(int id);
}
