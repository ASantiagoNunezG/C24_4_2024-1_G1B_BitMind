package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CicloRepository extends JpaRepository<Ciclo,Integer> {
    List<Ciclo> findByCarrera(Carrera carrera);
}
