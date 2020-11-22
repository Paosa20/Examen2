package com.cenfotec.examen2.repo;

import com.cenfotec.examen2.domain.Categorias;
import com.cenfotec.examen2.domain.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
}
