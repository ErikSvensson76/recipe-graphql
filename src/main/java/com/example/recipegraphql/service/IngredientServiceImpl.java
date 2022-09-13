package com.example.recipegraphql.service;

import com.example.recipegraphql.datasource.repositories.IngredientRepository;
import com.example.recipegraphql.datasource.service.IngredientMutationService;
import com.example.recipegraphql.exception.AppResourceNotFoundException;
import com.example.recipegraphql.model.entity.DBIngredient;
import com.example.recipegraphql.model.graphql.Ingredient;
import com.example.recipegraphql.model.graphql.IngredientInput;
import com.example.recipegraphql.service.converter.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientMutationService mutationService;
    private final IngredientRepository ingredientRepository;
    private final MappingService mappingService;

    @Override
    public Ingredient save(IngredientInput dto) {
        return mappingService.convert(mutationService.save(dto));
    }

    @Override
    public List<Ingredient> saveAll(List<IngredientInput> dtos) {
        return mutationService.saveAll(dtos).stream()
                .map(mappingService::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        mutationService.delete(id);
    }

    @Override
    public void deleteAll(List<String> ids) {
        mutationService.deleteAll(ids);
    }

    @Override
    public Ingredient findById(String id) {
        return mappingService.
                convert(ingredientRepository.findById(id).orElseThrow(AppResourceNotFoundException.of(DBIngredient.class, id)));
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll().stream()
                .map(mappingService::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> searchByIngredientName(String search) {
        return ingredientRepository.searchAllByIngredientName(search).stream()
                .map(mappingService::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Ingredient findByIngredientName(String ingredientName) {
        return mappingService.convert(ingredientRepository.findByIngredientName(ingredientName)
                .orElseThrow(() -> new AppResourceNotFoundException("Couldn't find ingredient with ingredientName: " + ingredientName)));
    }
}
