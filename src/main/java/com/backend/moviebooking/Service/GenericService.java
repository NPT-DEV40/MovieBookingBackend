package com.backend.moviebooking.Service;

import java.util.List;

public interface GenericService<T> {
    T save(T t);
    List<T> findAll();
    void delete(String id);
    T findById(String id);
}
