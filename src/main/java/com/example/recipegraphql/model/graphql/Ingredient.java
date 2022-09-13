package com.example.recipegraphql.model.graphql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
public class Ingredient implements Serializable {
    private String id;
    private String ingredientName;
}
