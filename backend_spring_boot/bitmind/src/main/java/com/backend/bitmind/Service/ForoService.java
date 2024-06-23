package com.backend.bitmind.Service;

import com.backend.bitmind.Model.Comentario;
import com.backend.bitmind.Model.Foro;
import com.backend.bitmind.Model.Usuario;
import com.backend.bitmind.Repository.ComentarioRepository;
import com.backend.bitmind.Repository.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForoService {
    @Autowired
    private ForoRepository foroRepository;

    public List<Foro> obtenerTodosLosForos() {

        List<Foro> foros = foroRepository.findAll();
        for (Foro foro : foros) {
            Usuario usuario = foro.getUsuario();
            if (usuario != null) {
                String nombreArchivo = usuario.getImagen();
                if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                    String urlCompleta = "https://bucketbitmind.s3.amazonaws.com/" + nombreArchivo;
                    usuario.setImagen(urlCompleta);
                }
            }
        }
        return foros;
    }

    public Foro guardarForo(Foro foro) {
        return foroRepository.save(foro);
    }

    // Otros métodos de servicio según sea necesario
}