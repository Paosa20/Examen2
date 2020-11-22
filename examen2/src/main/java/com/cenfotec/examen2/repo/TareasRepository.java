package com.cenfotec.examen2.repo;

import com.cenfotec.examen2.domain.Categorias;
import com.cenfotec.examen2.domain.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepository extends JpaRepository<Tareas, Long> {
}
