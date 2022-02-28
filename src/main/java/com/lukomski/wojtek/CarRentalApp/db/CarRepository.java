package com.lukomski.wojtek.CarRentalApp.db;

import com.lukomski.wojtek.CarRentalApp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    @Override
    <S extends Car> S save(S entity);

    @Query("select c from Car c where c.carId = ?1")
    Car findByCarId(Integer carId);

    Car findByBrand(String brand);

    List<Car> findAllByAvailable(boolean available);

    @Modifying //rodzaj querki ktore modyfikuje DB
    @Transactional //ma sie odbyc w jednej tranzakcji czyli na raz, zrob wszystko co jest w QUERCY a jesli sie nie powiedzie to wroc.(rollBack)
    @Query(value = "UPDATE Car car SET available=?1, dateOfStartRent =?2, dateOfEndRent=?3 WHERE id=?4")
    void updateDateOfRentWithAvailbility(boolean available, LocalDate dateOfStartRent, LocalDate dateOfEndRent, Integer carId);
}
