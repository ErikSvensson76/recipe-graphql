package com.example.recipegraphql.service.converter;

import com.example.recipegraphql.model.entity.DBIngredient;
import com.example.recipegraphql.model.graphql.Ingredient;
import com.example.recipegraphql.model.graphql.IngredientInput;

public interface MappingService {
    Ingredient convert(DBIngredient DBIngredient);

    DBIngredient convert(IngredientInput ingredientInput);
}
