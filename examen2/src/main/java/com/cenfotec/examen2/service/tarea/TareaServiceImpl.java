package com.cenfotec.examen2.service.tarea;

import com.cenfotec.examen2.domain.Tareas;
import com.cenfotec.examen2.repo.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    TareasRepository repo;

    @Override
    public void save(Tareas tareas) {
        repo.save(tareas);
    }

    @Override
    public Optional<Tareas> get(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Tareas> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Tareas> find(String name) {
        return null;
    }
}
