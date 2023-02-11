package com.example.BulletinBoard.controller;

import com.example.BulletinBoard.dto.BulletinBoardDTO;
import com.example.BulletinBoard.dto.ImageDTO;
import com.example.BulletinBoard.model.BulletinBoard;
import com.example.BulletinBoard.model.Image;
import com.example.BulletinBoard.service.BulletinBoardService;
import com.example.BulletinBoard.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/bulletin")
public class BulletinBoardController {
    private static final Logger logger = Logger.getGlobal();
    private BulletinBoardService bulletinBoardService;
    private ImageService imageService;

    @Autowired
    public BulletinBoardController(BulletinBoardService bulletinBoardService, ImageService imageService) {
        this.bulletinBoardService = bulletinBoardService;
        this.imageService = imageService;
    }

    @PostMapping("add/bulletin/{userid}/add")
    public ResponseEntity<HttpStatus> addBulletin(@RequestBody @Valid BulletinBoardDTO bulletinBoardDTO, @PathVariable("userid") int id){
        bulletinBoardService.save(convertToBulletinBoard(bulletinBoardDTO), id);
        logger.info("Добавление объявление с текстом и ценой");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("add/image/{bullitenId}/{userid}")
    public ResponseEntity<HttpStatus> addImage(@RequestBody @Valid ImageDTO imageDTO,
                                               @PathVariable("bullitenId") int bullitenId,
                                               @PathVariable("userid") int userid){
        imageService.save(convertToImage(imageDTO),bullitenId,userid);
        logger.info("Добавление картинки в объявление");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Image convertToImage(ImageDTO imageDTO) {
        Image image = new Image();
        image.setData(imageDTO.getData());
        return image;
    }

    private BulletinBoard convertToBulletinBoard(BulletinBoardDTO bulletinBoardDTO) {
        BulletinBoard bulletinBoard = new BulletinBoard();
        bulletinBoard.setDescription(bulletinBoardDTO.getDescription());
        bulletinBoard.setMinPrice(bulletinBoardDTO.getMinPrice());
        return bulletinBoard;
    }
}
