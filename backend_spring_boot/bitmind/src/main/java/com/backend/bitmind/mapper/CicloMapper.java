package com.backend.bitmind.mapper;

import com.backend.bitmind.Dtos.CicloDTO;
import com.backend.bitmind.Model.Ciclo;

public class CicloMapper {
    public static CicloDTO toDTO(Ciclo ciclo) {
        if (ciclo == null) {
            return null;
        }

        CicloDTO cicloDTO = new CicloDTO();
        cicloDTO.setIdCiclo(ciclo.getIdCiclo());
        cicloDTO.setNombre(ciclo.getNombre());
        return cicloDTO;
    }

    public static Ciclo toEntity(CicloDTO cicloDTO) {
        if (cicloDTO == null) {
            return null;
        }

        Ciclo ciclo = new Ciclo();
        ciclo.setIdCiclo(cicloDTO.getIdCiclo());
        ciclo.setNombre(cicloDTO.getNombre());
        return ciclo;
    }
}
