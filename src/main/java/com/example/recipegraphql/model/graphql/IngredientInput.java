package com.example.recipegraphql.model.graphql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientInput {

    private String id;
    @NotBlank
    private String ingredientName;
}
