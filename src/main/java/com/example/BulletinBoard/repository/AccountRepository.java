package com.example.BulletinBoard.repository;

import com.example.BulletinBoard.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account getAccountById(int id);
    Account findById(int id);
    //Account getAccountByEmail(String email);
    boolean existsByEmail(String email);
    Account findByEmail(String email);

    //oid deleteAccountByEmail(String email);

    @Modifying
    @Query("UPDATE Account a SET a.isActivate = :isActivate WHERE a.email = :email")
    void updateIsActiveByEmail(@Param("email") String email, @Param("isActivate") boolean isActive);

}
