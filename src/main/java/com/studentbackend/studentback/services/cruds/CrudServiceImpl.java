package com.studentbackend.studentback.services.cruds;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentbackend.studentback.services.interfaces.ICrudService;

public class CrudServiceImpl<Entity, Identifier> implements ICrudService<Entity, Identifier> {

    private JpaRepository<Entity, Identifier> repository;

    public CrudServiceImpl(JpaRepository<Entity, Identifier> repository) {
        this.repository = repository;
    }

    @Override
    public Entity save(Entity entity) {
        return repository.save(entity);
    }

    @Override
    public Entity update(Entity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Identifier id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Entity> findById(Identifier id) {
        return repository.findById(id);
    }

    @Override
    public List<Entity> findAll() {
        return repository.findAll();
    }
}
