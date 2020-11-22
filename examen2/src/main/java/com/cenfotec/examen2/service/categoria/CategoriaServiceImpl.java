package com.cenfotec.examen2.service.categoria;

import com.cenfotec.examen2.domain.Categorias;
import com.cenfotec.examen2.repo.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoriaServiceImpl implements CategoriaService {


    @Autowired
    CategoriasRepository repo;


    @Override
    public void save(Categorias categorias) {
        repo.save(categorias);
    }

    @Override
    public Optional<Categorias> get(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Categorias> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Categorias> find(String name) {
        return null;
    }

    @Override
    public void delete(Categorias categorias) {
        repo.delete(categorias);
    }
}
