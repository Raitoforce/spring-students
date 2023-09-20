package com.studentbackend.studentback.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IController<T, ID>{
    ResponseEntity<List<T>> fetchAll();
    ResponseEntity<T> fetchById(ID id);
    ResponseEntity<T> save(T entity);
    ResponseEntity<T> update(T entity);
    ResponseEntity<Void> delete(ID id);
}
