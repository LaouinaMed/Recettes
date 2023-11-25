package com.springboot.springmvc.dao;

import com.springboot.springmvc.entities.Recette;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Recette, Long> {

    public Page<Recette> findByNameContains(String mc , Pageable pageable);


}
