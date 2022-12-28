package com.example.aminebarguellil.entity;





import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  idParking;
    private String designation;
    private String adresse;
    private int capacite;

    @OneToMany(mappedBy = "parking",cascade = CascadeType.ALL)
    List<Zone> zones;

    @Override
    public String toString() {
        return "Parking{" +
                "idParking=" + idParking +
                ", designation='" + designation + '\'' +
                ", adresse='" + adresse + '\'' +
                ", capacite=" + capacite +
                '}';
    }
}
