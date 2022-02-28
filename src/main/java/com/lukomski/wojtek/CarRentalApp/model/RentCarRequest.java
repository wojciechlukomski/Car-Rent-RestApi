package com.lukomski.wojtek.CarRentalApp.model;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Data
public class RentCarRequest {
    private final boolean available;
    private final LocalDate dateOfStartRent;
    private final LocalDate dateOfEndRent;
    private final Integer userId;
    private final Integer carId;

}
