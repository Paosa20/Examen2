package com.cenfotec.examen2.service.workshop;

import com.cenfotec.examen2.domain.Workshop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface WorkshopService {

    public void save(Workshop workshop);
    public Optional<Workshop> get(Long id);
    public List<Workshop> getAll();
    public List<Workshop> find(String name);
    public List<Workshop> findCategoria(String name);
    public List<Workshop> findAutor(String name);
    public List<Workshop> findKeywords(String name);
}
