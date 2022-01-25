package com.xantx.financeapp.services.common;

import java.util.List;
import java.util.Optional;

public interface CrudService<Entity, Id> {
    Entity save(Entity entity) throws Exception;

    List<Entity> findAll() throws Exception;

    Optional<Entity> findById(Id id) throws Exception;

    Entity update(Id id, Entity entity) throws Exception;

    void deleteById(Id id) throws Exception;
}
