package com.backend.bitmind.mapper;

import com.backend.bitmind.Dtos.CarreraDTO;
import com.backend.bitmind.Model.Carrera;

public class CarreraMapper {
    public static CarreraDTO toDTO(Carrera carrera) {
        if (carrera == null) {
            return null;
        }

        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setIdCarrera(carrera.getIdCarrera());
        carreraDTO.setNombre(carrera.getNombre());
        return carreraDTO;
    }
}
