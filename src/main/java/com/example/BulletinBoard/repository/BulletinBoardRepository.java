package com.example.BulletinBoard.repository;

import com.example.BulletinBoard.dto.MaxPriceDTO;
import com.example.BulletinBoard.model.Account;
import com.example.BulletinBoard.model.BulletinBoard;
import com.example.BulletinBoard.model.MaxPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard,Integer> {
    MaxPrice findByMaxPrice(MaxPrice maxPrice);
    BulletinBoard findByAccount_Id(int id);
}
