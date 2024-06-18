package com.backend.bitmind.Repository;

import com.backend.bitmind.Model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository  extends JpaRepository <Archivo, Integer>{

}
