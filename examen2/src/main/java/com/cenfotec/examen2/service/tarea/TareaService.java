package com.cenfotec.examen2.service.tarea;

import com.cenfotec.examen2.domain.Tareas;
import com.cenfotec.examen2.domain.Workshop;

import java.util.List;
import java.util.Optional;

public interface TareaService {

    public void save(Tareas tareas);
    public Optional<Tareas> get(Long id);
    public List<Tareas> getAll();
    public List<Tareas> find(String name);
}
