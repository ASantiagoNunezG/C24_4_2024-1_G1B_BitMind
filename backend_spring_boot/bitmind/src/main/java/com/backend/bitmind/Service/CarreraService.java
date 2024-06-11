package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Carrera;
import com.backend.bitmind.Repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public List<Carrera> obtenerTodasLasCarreras() {
        return carreraRepository.findAll();
    }

    public Optional<Carrera> obtenerCarreraPorId(int idCarrera) {
        return carreraRepository.findById(idCarrera);
    }
}