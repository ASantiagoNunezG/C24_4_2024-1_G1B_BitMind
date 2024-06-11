package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Model.Ciclo;
import com.backend.bitmind.Repository.CicloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CicloService {


    @Autowired
    private CicloRepository cicloRepository;

    public List<Ciclo> obtenerTodosLosCiclos() {
        return cicloRepository.findAll();
    }

    public Optional<Ciclo> obtenerCicloPorId(int idCiclo) {
        return cicloRepository.findById(idCiclo);
    }
    public List<Ciclo> obtenerCiclosPorCarrera(Carrera carrera) {
        return cicloRepository.findByCarrera(carrera);
    }

}