package com.rodrigo.minhasfinancas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigo.minhasfinancas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//Query methods
    boolean existsByEmail(String email);
    
    // Equivalente a 
    //	Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
