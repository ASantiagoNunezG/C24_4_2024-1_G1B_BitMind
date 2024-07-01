package com.backend.bitmind.mapper;

import com.backend.bitmind.Dtos.ArchivoDTO;
import com.backend.bitmind.Model.Archivo;
import com.backend.bitmind.util.UrlUtil;

public class ArchivoMapper {
    public static ArchivoDTO toDTO(Archivo archivo) {
        if (archivo == null) {
            return null;
        }

        ArchivoDTO archivoDTO = new ArchivoDTO();
        archivoDTO.setIdArchivo(archivo.getIdArchivo());
        archivoDTO.setNombre(archivo.getNombre());
        archivoDTO.setUrl(UrlUtil.construirUrl(archivo.getUrl()));
        archivoDTO.setTipo(archivo.getTipo());
        archivoDTO.setPublicacion(PublicacionMapper.toDTO(archivo.getPublicacion()));

        return archivoDTO;
    }

    public static Archivo toEntity(ArchivoDTO archivoDTO) {
        if (archivoDTO == null) {
            return null;
        }

        Archivo archivo = new Archivo();
        archivo.setIdArchivo(archivoDTO.getIdArchivo());
        archivo.setNombre(archivoDTO.getNombre());
        archivo.setUrl(archivoDTO.getUrl());
        archivo.setTipo(archivoDTO.getTipo());
        archivo.setPublicacion(PublicacionMapper.toEntity(archivoDTO.getPublicacion()));

        return archivo;
    }
}