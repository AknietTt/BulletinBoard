package com.example.BulletinBoard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "BulletinBoard")
public class BulletinBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="description")
    private String description;

    @Column(name = "minPrice")
    @NotNull
    private int minPrice;

    @ManyToOne
    @JoinColumn(name = "maxPrice_id", referencedColumnName = "id")
    private MaxPrice maxPrice;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @NotNull
    private Account account;
    @OneToOne(mappedBy = "bulletinBoard")
    private Image image;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account user) {
        this.account = user;
    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }


    public MaxPrice getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(MaxPrice maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }
}
