package com.backend.bitmind.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity // Marca la clase como una entidad JPA.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Define la estrategia de herencia. En este caso, una sola tabla para todas las clases.
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING) // Define la columna de discriminador.
@Table(name = "usuarios")
public abstract class Usuario { // Clase abstracta que no puede ser instanciada directamente.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario; // Identificador único de cada usuario.
    @Column(name = "nombres")
    private String nombres; // Nombre del usuario.
    @Column(name = "correo", unique = true)
    private String correo; // Correo del usuario.
    @Column(name = "rol")
    private String roles; // Rol del usuario en el sistema.

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Foro> foros;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Anuncio> anuncios;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Publicacion> publicaciones;

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Foro> getForos() {
        return foros;
    }

    public void setForos(List<Foro> foros) {
        this.foros = foros;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}