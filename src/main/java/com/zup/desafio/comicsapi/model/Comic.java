package com.zup.desafio.comicsapi.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

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

    private DayOfWeek discountDay;

    private boolean isActiveDiscount;

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

        setDiscountDay(this.isbn);
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

    public DayOfWeek getDiscountDay() {
        return discountDay;
    }

    private void setDiscountDay(String isbn) {
        if (isbn != null && !isbn.equals("")) {
            String isbnLastDigit = isbn.substring(isbn.length() - 1);

            if (isbnLastDigit.equals("0") || isbnLastDigit.equals("1")) {
                this.discountDay = DayOfWeek.MONDAY;
            }

            if (isbnLastDigit.equals("2") || isbnLastDigit.equals("3")) {
                this.discountDay = DayOfWeek.TUESDAY;
            }

            if (isbnLastDigit.equals("4") || isbnLastDigit.equals("5")) {
                this.discountDay = DayOfWeek.WEDNESDAY;
            }

            if (isbnLastDigit.equals("6") || isbnLastDigit.equals("7")) {
                this.discountDay = DayOfWeek.THURSDAY;
            }

            if (isbnLastDigit.equals("8") || isbnLastDigit.equals("9")) {
                this.discountDay = DayOfWeek.FRIDAY;
            }
        }
    }

    public boolean isActiveDiscount() {
        isActiveDiscount = this.discountDay == LocalDate.now().getDayOfWeek();
        return isActiveDiscount;
    }

}
