package com.cenfotec.examen2.service.categoria;

import com.cenfotec.examen2.domain.Categorias;
import com.cenfotec.examen2.domain.Workshop;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    public void save(Categorias categorias);
    public Optional<Categorias> get(Long id);
    public List<Categorias> getAll();
    public List<Categorias> find(String name);
    public void delete(Categorias categorias);
}
