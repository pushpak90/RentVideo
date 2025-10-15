package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{
    List<Rental> findByUserId(long userId);
}
