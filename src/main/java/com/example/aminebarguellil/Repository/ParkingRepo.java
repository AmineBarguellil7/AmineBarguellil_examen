package com.example.aminebarguellil.Repository;

import com.example.aminebarguellil.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepo extends JpaRepository<Parking,Integer> {
    public Parking findByAdresse(String adresse);
}
