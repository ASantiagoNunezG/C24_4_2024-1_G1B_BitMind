package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
}
