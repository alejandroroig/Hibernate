package org.ejemplos.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T create(T t);
    Optional<T> read(int id);
    List<T> readAll();
    T update(T t);
    void delete(T t);
}
