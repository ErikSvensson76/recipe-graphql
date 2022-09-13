package com.example.recipegraphql.datasource.service;

import com.example.recipegraphql.datasource.repositories.IngredientRepository;
import com.example.recipegraphql.exception.AppResourceNotFoundException;
import com.example.recipegraphql.model.entity.DBIngredient;
import com.example.recipegraphql.model.graphql.IngredientInput;
import com.example.recipegraphql.service.converter.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientMutationServiceImpl implements IngredientMutationService{

    private final IngredientRepository repository;
    private final MappingService mappingService;

    @Override
    @Transactional
    public DBIngredient save(IngredientInput dto) {
        if(dto == null) throw new IllegalArgumentException("Ingredient dto was null");
        if(dto.getId() != null){
            return update(dto.getId(), dto);
        }
        return create(dto);
    }

    @Override
    @Transactional
    public List<DBIngredient> saveAll(List<IngredientInput> dtos) {
        return dtos.stream()
                .map(dto -> dto.getId() == null ? create(dto) : update(dto.getId(), dto))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll(List<String> ids) {
        repository.deleteAllById(ids);
    }

    @Transactional
    public DBIngredient create(IngredientInput dto){
        return repository.save(mappingService.convert(dto));
    }

    @Transactional
    public DBIngredient update(String id, IngredientInput dto){
        DBIngredient DBIngredient = repository.findById(id)
                .orElseThrow(AppResourceNotFoundException.of(DBIngredient.class, id));


        return repository.save(copyFromCommand(dto, DBIngredient));
    }

    public DBIngredient copyFromCommand(IngredientInput source, DBIngredient target){
        target.setIngredientName(source.getIngredientName().trim());
        return target;
    }
}
