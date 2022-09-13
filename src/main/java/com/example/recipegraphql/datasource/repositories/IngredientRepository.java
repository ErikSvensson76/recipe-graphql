package com.example.recipegraphql.datasource.repositories;

import com.example.recipegraphql.model.entity.DBIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<DBIngredient, String> {

    @Query(value = "SELECT i FROM DBIngredient i WHERE UPPER(i.ingredientName) = UPPER(:ingredientName)")
    Optional<DBIngredient> findByIngredientName(@Param("ingredientName") String ingredientName);

    @Query(value = "SELECT i FROM DBIngredient i WHERE UPPER(i.ingredientName) LIKE UPPER(CONCAT('%',:search,'%'))")
    List<DBIngredient> searchAllByIngredientName(@Param("search") String search);

}
