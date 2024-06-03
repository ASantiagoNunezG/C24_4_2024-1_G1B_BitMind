package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Archivo;
import com.backend.bitmind.Repository.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    public List<Archivo> obtenerTodosLosArchivos() {
        return archivoRepository.findAll();
    }

    public Archivo obtenerArchivoPorId(int id) {
        return archivoRepository.findById(id).orElse(null);
    }

    public Archivo guardarArchivo(Archivo archivo) {
        return archivoRepository.save(archivo);
    }

    public void eliminarArchivo(int id) {
        archivoRepository.deleteById(id);
    }
}