package com.cenfotec.examen2.repo;

import com.cenfotec.examen2.domain.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
    public List<Workshop> findByCategoriaContaining(String word);
    public List<Workshop> findByAutorContaining(String word);
    public List<Workshop> findByKeywordContaining(String word);

}
