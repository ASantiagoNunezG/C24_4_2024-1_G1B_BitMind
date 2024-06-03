package com.backend.bitmind.Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OAUTH2")
public class UsuarioOAuth2 extends Usuario {

    @Column(name = "google_id")
    private String googleId;
    @Column(name = "imagen")
    private String imagen;

    // Getters y setters
    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}