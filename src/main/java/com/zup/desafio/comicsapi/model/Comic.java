package com.zup.desafio.comicsapi.model;

import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idFromMarvel;

    private String title;

    private Double price;

    private String creators;

    private String isbn;

    @Column(length = 1000)
    private String description;

    public Comic() {
    }

    public Comic(Long id, Long idFromMarvel, String title, Double price, String creators, String isbn, String description) {
        this.id = id;
        this.idFromMarvel = idFromMarvel;
        this.title = title;
        this.price = price;
        this.creators = creators;
        this.isbn = isbn;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFromMarvel() {
        return idFromMarvel;
    }

    public void setIdFromMarvel(Long idFromMarvel) {
        this.idFromMarvel = idFromMarvel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
