package com.example.recipegraphql.controller;

import com.example.recipegraphql.model.graphql.Ingredient;
import com.example.recipegraphql.model.graphql.IngredientInput;
import com.example.recipegraphql.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class IngredientController {

    private final IngredientService ingredientService;

    @MutationMapping
    public Ingredient saveIngredient( @Argument(name = "ingredientInput") @Valid IngredientInput ingredientInput){
        return ingredientService.save(ingredientInput);
    }

    @MutationMapping
    public List<Ingredient> saveIngredients(@Argument(name = "ingredientsInputs") List<IngredientInput> ingredientInputs){
        return ingredientService.saveAll(ingredientInputs);
    }

    @QueryMapping
    public Ingredient findIngredientById(@Argument(name = "id") String id){
        return ingredientService.findById(id);
    }



}
