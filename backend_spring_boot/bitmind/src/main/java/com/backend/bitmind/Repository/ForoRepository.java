package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Integer> {
    List<Foro> findAllByOrderByFechaCreacionDesc();
}