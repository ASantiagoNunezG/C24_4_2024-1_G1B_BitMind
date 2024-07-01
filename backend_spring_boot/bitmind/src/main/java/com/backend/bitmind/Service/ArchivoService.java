package com.backend.bitmind.Service;

import com.backend.bitmind.Dtos.ArchivoDTO;
import com.backend.bitmind.Model.Archivo;
import com.backend.bitmind.Model.Publicacion;
import com.backend.bitmind.Repository.ArchivoRepository;
import com.backend.bitmind.Repository.PublicacionRepository;
import com.backend.bitmind.mapper.ArchivoMapper;
import com.backend.bitmind.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<ArchivoDTO> obtenerTodosLosArchivos() {
        List<Archivo> archivos = archivoRepository.findAll();
        List<ArchivoDTO> archivosDTO = new ArrayList<>();
        for (Archivo archivo : archivos) {
            archivosDTO.add(ArchivoMapper.toDTO(archivo));
        }
        return archivosDTO;
    }

    public ArchivoDTO obtenerArchivoPorId(int id) {
        Archivo archivo = archivoRepository.findById(id).orElse(null);
        return ArchivoMapper.toDTO(archivo);
    }

    public ArchivoDTO guardarArchivo(ArchivoDTO archivoDTO, String correoUsuario) {
        if (archivoDTO.getPublicacion() == null) {
            throw new RuntimeException("Publicacion no puede ser nula");
        }

        Publicacion publicacion = publicacionRepository.findById(archivoDTO.getPublicacion().getIdPublicacion())
                .orElseThrow(() -> new RuntimeException("Publicacion no encontrada"));

        if (!publicacion.getUsuario().getCorreo().equals(correoUsuario)) {
            throw new RuntimeException("No tienes permiso para agregar archivos a esta publicación");
        }

        Archivo archivo = ArchivoMapper.toEntity(archivoDTO);
        archivo.setPublicacion(publicacion);
        archivo = archivoRepository.save(archivo);
        return ArchivoMapper.toDTO(archivo);
    }

    public ArchivoDTO actualizarArchivo(ArchivoDTO archivoDTO, int id, String correoUsuario) {
        Archivo archivoExistente = archivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        if (!archivoExistente.getPublicacion().getUsuario().getCorreo().equals(correoUsuario)) {
            throw new RuntimeException("No tienes permiso para actualizar este archivo");
        }

        archivoExistente.setNombre(archivoDTO.getNombre());
        archivoExistente.setUrl(archivoDTO.getUrl());
        archivoExistente.setTipo(archivoDTO.getTipo());
        archivoExistente = archivoRepository.save(archivoExistente);
        return ArchivoMapper.toDTO(archivoExistente);
    }

    public void eliminarArchivo(int id, String correoUsuario) {
        Archivo archivo = archivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        if (!archivo.getPublicacion().getUsuario().getCorreo().equals(correoUsuario)) {
            throw new RuntimeException("No tienes permiso para eliminar este archivo");
        }

        archivoRepository.deleteById(id);
    }
}