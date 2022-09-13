package com.example.recipegraphql.service.converter;

import com.example.recipegraphql.model.entity.DBIngredient;
import com.example.recipegraphql.model.graphql.Ingredient;
import com.example.recipegraphql.model.graphql.IngredientInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MappingConverter implements MappingService {
    @Override
    public Ingredient convert(DBIngredient DBIngredient) {
        Ingredient ingredient = null;
        if(DBIngredient != null){
            ingredient = new Ingredient();
            ingredient.setId(DBIngredient.getId());
            ingredient.setIngredientName(DBIngredient.getIngredientName());
        }
        return ingredient;
    }

    @Override
    public DBIngredient convert(IngredientInput ingredientInput){
        DBIngredient dbIngredient = null;
        if(ingredientInput != null){
            dbIngredient = new DBIngredient();
            dbIngredient.setId(ingredientInput.getId());
            dbIngredient.setIngredientName(ingredientInput.getIngredientName());
        }
        return dbIngredient;
    }


}
