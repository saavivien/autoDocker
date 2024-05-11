package com.vvs.auto.ressource;

import com.vvs.auto.model.Vehicule;
import com.vvs.auto.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicule")
public class VehiculeRessource {
    @Autowired
    private VehiculeRepository vehiculeRepository;

    @GetMapping
    ResponseEntity<List<Vehicule>> getVehicule() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        if (vehicules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Vehicule>>(vehicules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Vehicule> getVehiculeById(@PathVariable("id") long id) {
        if (vehiculeRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Vehicule>((Vehicule) vehiculeRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Void> saveVehicule(@RequestBody Vehicule vehicule) {
        try {
            vehiculeRepository.save(vehicule);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Vehicule> updateVehicule(@PathVariable("id") long id, @RequestBody(required = true) Vehicule vehicule) {
        Vehicule vehicule1 = vehiculeRepository.findById(id).map(v -> {
            v.setAnneeProduction(vehicule.getAnneeProduction());
            v.setMarque(vehicule.getMarque());
            v.setModel(vehicule.getModel());
            return vehiculeRepository.save(v);
        }).orElseGet(() -> {
                    Vehicule v = new Vehicule();
                    v.setAnneeProduction(vehicule.getAnneeProduction());
                    v.setMarque(vehicule.getMarque());
                    v.setModel(vehicule.getModel());
                    return vehiculeRepository.save(v);
                }
        );
        return ResponseEntity.ok(vehicule1);
    }
}
