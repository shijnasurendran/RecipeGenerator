package com.recipe.generator.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.recipe.generator.model.Ingredient;
import com.recipe.generator.model.User;

public interface RecipeDAO {

	public int insertUser(User user);
	
	public User checkUser(User user) throws DataAccessException, Exception;
	
	public List<Ingredient> getMyFridge(User user);
	
	public int addIngredients(User user, String[] ingredients) throws DataAccessException;
	
	public int removeIngredient(User user, String ingredient) throws DataAccessException;
	
	public String toggleFavorite(User user, String rid) throws DataAccessException;
	
	public List<String> getFavoriteRecipesIds(User user) throws DataAccessException;
}
