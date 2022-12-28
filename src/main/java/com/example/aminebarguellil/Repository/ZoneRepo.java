package com.example.aminebarguellil.Repository;

import com.example.aminebarguellil.entity.Parking;
import com.example.aminebarguellil.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ZoneRepo extends JpaRepository<Zone,Integer> {
    List<Zone>  findByParking(Parking parking);
}
