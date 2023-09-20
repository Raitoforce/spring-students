package com.studentbackend.studentback.services.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents a CRUD service.
 */
public interface ICrudService<T, ID> {
    
    /**
     * Saves a record.
     * 
     * @param t the record to be saved
     */
    T save(T t);

    /**
     * Updates a record.
     * 
     * @param t the record to be updated
     */
    T update(T t);

    /**
     * Deletes a record.
     * 
     * @param id the ID of the record to delete
     */
    void delete(ID id);

    /**
     * Finds a record by ID.
     * 
     * @param id the ID of the record to find
     * @return the found record, or null if not found
     */
    Optional<T> findById(ID id);

    /**
     * Finds all records.
     * 
     * @return a list of all records
     */
    List<T> findAll();
}
