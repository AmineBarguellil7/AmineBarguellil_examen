package com.example.aminebarguellil.Controller;


import com.example.aminebarguellil.Repository.ParkingRepo;
import com.example.aminebarguellil.Repository.PersonnelRepo;
import com.example.aminebarguellil.Repository.ZoneRepo;
import com.example.aminebarguellil.entity.Parking;
import com.example.aminebarguellil.entity.Personnel;
import com.example.aminebarguellil.entity.Zone;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@RequestMapping("/exam")
@RestController
@Slf4j
public class controller {

    private PersonnelRepo personnelRepo;
    private ParkingRepo parkingRepo;
    private ZoneRepo zoneRepo;

    @PostMapping("/ajouterpersonnel")
    public Personnel ajouterPersonnel (@RequestBody Personnel personnel) {
        return personnelRepo.save(personnel);
    }


    @PostMapping("/ajouterparking")
    public void ajoutParkingetZones(@RequestBody Parking parking) {
        parkingRepo.save(parking);
        List<Zone> zones=parking.getZones();
        if (zones!= null) {
            for (Zone zone:zones) {
                zone.setParking(parking);
                zoneRepo.save(zone);
            }
        }
    }

    @GetMapping("/affecterPersonnelZone/{idzone}/{idGarde}")
    void affecterPersonnelZone(@PathVariable int idzone,@PathVariable int idGarde) {
        Personnel garde=personnelRepo.findById(idGarde).orElse(null);
        Zone zone=zoneRepo.findById(idzone).orElse(null);
        zone.getGardiens().add(garde);
        zoneRepo.save(zone);
    }

    @GetMapping("")
    public  List<Personnel> getAllPersonnelByParking(@RequestBody Parking parking) {
        return null;
    }

    @GetMapping("nombreGardeJour")
    public Integer nombreGardeJour(@PathVariable String adresse) {
        Parking parking=parkingRepo.findByAdresse(adresse);
        int nbrgardes=0;
        List<Zone> zones=parking.getZones();
        for (Zone zone:zones) {
            List<Personnel> gardes=zone.getGardiens();
            for (Personnel garde:gardes) {
                if (garde.getPoste().equals("GARDE_JOUR")) {
                    nbrgardes+=1;
                }
            }
        }
        return nbrgardes;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void getNbrGardesByZone() {
        List<Zone> zones = zoneRepo.findAll();
        for (Zone z : zones) {
            int nbrgardes = 0;
            List<Personnel> gardes = z.getGardiens();
            for (Personnel garde : gardes) {
                if (garde.getPoste().equals("GARDE_JOUR")) {
                    nbrgardes += 1;
                    log.info("dans la zone "+z+"le nombre des gardes jours est "+nbrgardes);
                }
            }
        }
    }
    @GetMapping("getPersonalByDate/{startDate}/{endDate}")
    public List<Personnel> getPersonalByDate(@PathVariable Date startDate,@PathVariable Date endDate) {
        List<Personnel> personnels=personnelRepo.findAll();
        List<Personnel> listPersonnels=new ArrayList<>();
        for (Personnel personnel:personnels) {
            if (personnel.getDateDeRecrutement().after(startDate) && personnel.getDateDeRecrutement().before(endDate)) {
                listPersonnels.add(personnel);
            }
        }
        return listPersonnels;
    }

}
