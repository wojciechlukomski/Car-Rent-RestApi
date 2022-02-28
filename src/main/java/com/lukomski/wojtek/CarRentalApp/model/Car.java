package com.lukomski.wojtek.CarRentalApp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity //pojedyncza encja w bazie danych
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tworzy incrementacje
    private Integer carId;

    @Column
    private String brand;

    @Column
    private String year;

    @Column
    private BigDecimal pricePerDay;

    @Column
    private LocalDate dateOfStartRent;

    @Column
    private LocalDate dateOfEndRent;

    @Column
    private Boolean available = true;
}
