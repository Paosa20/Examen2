package com.cenfotec.examen2.service.workshop;

import com.cenfotec.examen2.domain.Workshop;
import com.cenfotec.examen2.repo.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    WorkshopRepository repo;

    @Override
    public void save(Workshop workshop) {
        repo.save(workshop);
    }

    @Override
    public Optional<Workshop> get(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Workshop> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Workshop> find(String name) {
        return null;
    }

    @Override
    public List<Workshop> findCategoria(String name) {
        return repo.findByCategoriaContaining(name);
    }

    @Override
    public List<Workshop> findAutor(String name) {
        return repo.findByAutorContaining(name);
    }

    @Override
    public List<Workshop> findKeywords(String name) {
        return repo.findByKeywordContaining(name);
    }


}
