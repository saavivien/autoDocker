package com.vvs.auto.repository;

import com.vvs.auto.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    Optional<Vehicule> findById(String id);

    Optional<Vehicule> findByMarque(String marque);

    Optional<Vehicule> findByModel(String model);
//    StringBuilder sb = new StringBuilder();
//    sb.
}
