package com.lukomski.wojtek.CarRentalApp.db;

import com.lukomski.wojtek.CarRentalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {

    @Override
    <S extends User> S save(S entity); // tego nie musi tutaj byc

    @Override
    void deleteById(Integer userId); // tego override tez nie musi tutaj byc

    @Query("select u from User u where u.login = ?1")
    User findByLogin(String login);

    @Override
    Optional<User> findById(Integer integer);

    User findByUserId(Integer userId);

    List<User> findAll();


}






