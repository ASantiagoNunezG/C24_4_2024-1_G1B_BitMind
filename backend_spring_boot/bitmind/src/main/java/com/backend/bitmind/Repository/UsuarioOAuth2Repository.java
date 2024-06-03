package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.UsuarioOAuth2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioOAuth2Repository extends JpaRepository<UsuarioOAuth2, Long> {
    Optional<UsuarioOAuth2> findByGoogleId(String googleId);
}