package com.example.recipegraphql.datasource.service;

import java.util.List;

public interface GenericMutation<T, R, ID> {

    R save(T dto);
    List<R> saveAll(List<T> dtos);
    void delete(ID id);
    void deleteAll(List<ID> ids);

}
