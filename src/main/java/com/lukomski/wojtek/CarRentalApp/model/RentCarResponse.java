package com.lukomski.wojtek.CarRentalApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class RentCarResponse {
    private final Car car;
    private final User user;
    private final BigDecimal price;
}
