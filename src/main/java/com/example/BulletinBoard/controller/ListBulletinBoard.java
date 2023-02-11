package com.example.BulletinBoard.controller;

import com.example.BulletinBoard.dto.MaxPriceDTO;
import com.example.BulletinBoard.model.BulletinBoard;
import com.example.BulletinBoard.model.MaxPrice;
import com.example.BulletinBoard.service.BulletinBoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class ListBulletinBoard {
    private BulletinBoardService bulletinBoardService;

    @Autowired
    public ListBulletinBoard(BulletinBoardService bulletinBoardService) {
        this.bulletinBoardService = bulletinBoardService;
    }

    @GetMapping()
    public List<BulletinBoard> getAllListBulletin (){
        return bulletinBoardService.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/raise-price/{buyerId}/{sellerId}")
    public ResponseEntity<HttpStatus> raisePrice(@RequestBody @Valid MaxPriceDTO maxPriceDTO,
                                                  @PathVariable("{buyerId}") int buyerId,
                                                  @PathVariable("{sellerId") int sellerId){

        bulletinBoardService.raisePrice(convertToMaxPrice(maxPriceDTO), buyerId,sellerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-bulletin/{bulletinId}")
    public ResponseEntity<HttpStatus> deleteBulletin(@PathVariable("bulletinId") int bulletinId ){
        bulletinBoardService.delete(bulletinId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private MaxPrice convertToMaxPrice(MaxPriceDTO maxPriceDTO) {
        MaxPrice maxPrice = new MaxPrice();
        maxPrice.setMaxPrice(maxPrice.getMaxPrice());
        return  maxPrice;
    }
}
