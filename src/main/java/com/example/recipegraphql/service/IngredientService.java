package com.example.recipegraphql.service;

import com.example.recipegraphql.datasource.service.GenericMutation;
import com.example.recipegraphql.model.graphql.Ingredient;
import com.example.recipegraphql.model.graphql.IngredientInput;

import java.util.List;

public interface IngredientService extends GenericMutation<IngredientInput, Ingredient, String> {

    Ingredient findById(String id);

    List<Ingredient> findAll();

    List<Ingredient> searchByIngredientName(String search);

    Ingredient findByIngredientName(String ingredientName);
}
