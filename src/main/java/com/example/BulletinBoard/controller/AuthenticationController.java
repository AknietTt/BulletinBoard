package com.example.BulletinBoard.controller;
import com.example.BulletinBoard.dto.AccountDTO;
import com.example.BulletinBoard.dto.AccountLoginDTO;
import com.example.BulletinBoard.model.Account;
import com.example.BulletinBoard.service.AccountService;
import com.example.BulletinBoard.util.HashPassword;
import com.example.BulletinBoard.util.UserNotCreatedExeption;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private static final Logger logger = Logger.getGlobal();
    private final AccountService accountService;
    private final HashPassword hashPassword;
    @Autowired
    public AuthenticationController(AccountService accountService, HashPassword hashPassword) throws NoSuchAlgorithmException {
        this.accountService = accountService;
        this.hashPassword = hashPassword;
    }
    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signUpUser(@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) throws NoSuchAlgorithmException {

        // проверка ввода данных от пользователя
        if((bindingResult.hasErrors()  || !accountDTO.getPassword().equals(accountDTO.getRepeatPassword()) || accountService.isAccount(accountDTO.getEmail()))){
            StringBuilder errMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
               errMsg.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
            }
            throw new UserNotCreatedExeption(errMsg.toString());
        }
        accountService.save(convertToAccountDTO(accountDTO));
        // отпрака ссылку в емайл
        logger.info("Send message in Email user http://localhost:8080/authentication/create/"+accountDTO.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/create/{email}")
    public ResponseEntity<HttpStatus> createUser(@PathVariable("email") String email ){
        if(accountService.isAccount(email) == false){
            logger.info("Ошибка при созданий Аккаунта");
            throw new UserNotCreatedExeption("User not Created ");
        }
        accountService.updateIsActiveByEmail(email,true);
        logger.info("Аккаунт актевирован");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<HttpStatus> logIn(@RequestBody @Valid AccountLoginDTO accountLoginDTO) throws NoSuchAlgorithmException {
        String checkPassword =accountService.findAccountByEmail(accountLoginDTO.getEmail()).getPassword();
        if(!checkPassword.equals(hashPassword.getPassword(accountLoginDTO.getPassword()))){
            logger.info("Не парвильный пароль или емайл");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }
        logger.info("Успешный вход в аккаунт");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Account convertToAccountDTO(AccountDTO accountDTO) throws NoSuchAlgorithmException {
        Account user  = new Account();
        user.setEmail(accountDTO.getEmail());
        user.setPassword(hashPassword.getPassword(accountDTO.getPassword()));
        user.setUsername(accountDTO.getUsername());
        return  user;
    }



}
