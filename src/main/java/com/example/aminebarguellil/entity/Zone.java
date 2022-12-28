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
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idZone;
    private String ref;
    private float dimension;


    @ManyToOne(cascade=CascadeType.ALL)
    private  Parking parking;



    @OneToMany(fetch = FetchType.EAGER)
    List<Personnel> gardiens;
    @OneToOne
    private Personnel responsable;

    @Override
    public String toString() {
        return "Zone{" +
                "idZone=" + idZone +
                ", ref='" + ref + '\'' +
                ", dimension=" + dimension +
                ", parking=" + parking +
                ", gardiens=" + gardiens +
                '}';
    }
}
