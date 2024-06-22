package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo,Integer> {
}
