package com.asusoftware.tacocloud.repository;

import com.asusoftware.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
