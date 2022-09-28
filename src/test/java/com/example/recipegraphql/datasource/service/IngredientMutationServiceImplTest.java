package com.example.recipegraphql.datasource.service;

import com.example.recipegraphql.model.entity.DBIngredient;
import com.example.recipegraphql.model.graphql.IngredientInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class IngredientMutationServiceImplTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    IngredientMutationServiceImpl testObject;

    @Test
    @DisplayName("GIVEN input WHEN input.ingredientName is not found THEN save new DBIngredient AND return result")
    void save_create() {
        IngredientInput input = new IngredientInput(null, "Tomater");

        var result = testObject.save(input);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    @DisplayName("GIVEN input WHEN input.ingredientName is found THEN return found result")
    void save_create_fetch_ingredient() {
        String ingredientName = "Tomater";
        var existingIngredient = em.persist(new DBIngredient(null, ingredientName));

        var result = testObject.save(new IngredientInput(null, ingredientName));

        assertNotNull(result);
        assertEquals(existingIngredient.getId(), result.getId());
        assertEquals(existingIngredient.getIngredientName(), result.getIngredientName());
    }

    @Test
    @DisplayName("GIVEN input WHEN another ingredient is not found by name THEN update DBIngredient AND return result")
    void save_updates_existing_ingredient() {
        var originalIngredient = em.persist(new DBIngredient(null, "Test2"));
        String ingredientName = "Test";
        IngredientInput input = new IngredientInput(originalIngredient.getId(), ingredientName);

        var result = testObject.save(input);

        assertNotNull(result);
        assertEquals(originalIngredient.getId(), result.getId());
        assertEquals(ingredientName, result.getIngredientName());
    }

    @Test
    @DisplayName("GIVEN input WHEN another ingredient with same name is found THEN return found DBIngredient")
    void save_when_update_return_another_ingredient_with_same_name() {
        String ingredientName = "Test2";
        em.persist(new DBIngredient(null, ingredientName));

        var original = em.persist(new DBIngredient(null, "Test"));
        IngredientInput input = new IngredientInput(original.getId(), ingredientName);

        var result = testObject.save(input);

        assertNotNull(result);
        assertNotEquals(original.getId(), result.getId());
        assertEquals(ingredientName, result.getIngredientName());
    }
}