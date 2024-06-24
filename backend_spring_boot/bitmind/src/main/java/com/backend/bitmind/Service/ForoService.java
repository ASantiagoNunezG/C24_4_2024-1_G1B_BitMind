package com.backend.bitmind.Service;

import com.backend.bitmind.Dtos.ForoDTO;
import com.backend.bitmind.Model.Comentario;
import com.backend.bitmind.Model.Foro;
import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Repository.ComentarioRepository;
import com.backend.bitmind.Repository.ForoRepository;
import com.backend.bitmind.mapper.ForoMapper;
import com.backend.bitmind.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForoService {
    @Autowired
    private ForoRepository foroRepository;

    public List<ForoDTO> obtenerTodosLosForos() {
        List<Foro> foros = foroRepository.findAllByOrderByFechaCreacionDesc();
        List<ForoDTO> forosDTO = new ArrayList<>();

        for (Foro foro : foros) {
            forosDTO.add(ForoMapper.toDTO(foro));
        }

        return forosDTO;
    }

    public Foro guardarForo(Foro foro) {
        return foroRepository.save(foro);
    }

    //Servicio para obtener el foro por id
    public Foro obtenerForoPorId(int id){
        return foroRepository.findById(id).orElse(null);
    }
}