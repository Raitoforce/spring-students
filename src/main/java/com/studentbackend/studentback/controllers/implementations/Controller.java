package com.studentbackend.studentback.controllers.implementations;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.studentbackend.studentback.controllers.interfaces.IController;
import com.studentbackend.studentback.services.interfaces.ICrudService;

public class Controller<Entity, Identifier> implements IController<Entity, Identifier> {

    private ICrudService<Entity, Identifier> crudService;

    Controller(ICrudService<Entity, Identifier> crudService) {
        this.crudService = crudService;
    }

    @Override
    public ResponseEntity<List<Entity>> fetchAll() {
        List<Entity> entities = crudService.findAll();
        return ResponseEntity.ok(entities);
    }

    @Override
    public ResponseEntity<Entity> fetchById(Identifier id) {
        Entity entity = crudService.findById(id).orElse(null);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @Override
    public ResponseEntity<Entity> save(Entity entity) {

        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }

        Entity savedEntity = crudService.save(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @Override
    public ResponseEntity<Entity> update(Entity entity) { 
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Entity updatedEntity = crudService.update(entity);
        return ResponseEntity.ok(updatedEntity);
    }

    @Override
    public ResponseEntity<Void> delete(Identifier id) {
        Entity entity = crudService.findById(id).orElse(null);

        if(entity == null){
            return ResponseEntity.notFound().build();
        }

        crudService.delete(id);
        return ResponseEntity.ok(null);
    }
    
}
