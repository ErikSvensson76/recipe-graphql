package com.example.recipegraphql.datasource.service;

import com.example.recipegraphql.model.entity.DBIngredient;
import com.example.recipegraphql.model.graphql.IngredientInput;

public interface IngredientMutationService extends GenericMutation<IngredientInput, DBIngredient, String> {

}
