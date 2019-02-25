package com.recipe.generator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.recipe.generator.dao.RecipeDAOImpl;
import com.recipe.generator.model.Ingredient;
import com.recipe.generator.model.RecipeResponse;
import com.recipe.generator.model.User;

@Controller
@SessionAttributes("sessionUser")
public class RecipeGeneratorController {

	@Autowired
	RecipeDAOImpl recipeDaoImpl;

	@RequestMapping("/index")
	public ModelAndView helloWorld() {
		//return new ModelAndView("index");
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping("/login")
	public ModelAndView toLoginPage() {
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping("/logout")
	public ModelAndView logout(ModelMap model) {
		model.remove("sessionUser");
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping("/registration")
	public ModelAndView userRegistration() {
		return new ModelAndView("userRegistration", "user", new User());
	}

	@RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
	public ModelAndView showForm(@ModelAttribute("employee") User user, BindingResult result, ModelMap model) {

		int rtrn = recipeDaoImpl.insertUser(user);
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/addIngredients", method = RequestMethod.GET)
	public @ResponseBody String addIngredients(@RequestParam(value = "ingredients") String ing,
			@SessionAttribute("sessionUser") User user) {

		String[] ingredients = ing.split(",");
		try {
			recipeDaoImpl.addIngredients(user, ingredients);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			return "database error";
		}
		// int rtrn = recipeDaoImpl.insertUser(user);
		return "Ingredients added";
	}

	@RequestMapping(value = "/removeIngredient", method = RequestMethod.GET)
	public @ResponseBody String removeIngredient(@RequestParam(value = "ingredient") String ing,
			@SessionAttribute("sessionUser") User user) {
		try {
			recipeDaoImpl.removeIngredient(user, ing);
		} catch (DataAccessException ex) {
			return "Database error";
		}
		return "Ingredient Removed";

	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)

	public ModelAndView submit(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		User u = null;
		try {
			u = recipeDaoImpl.checkUser(user);
		} catch (DataAccessException ex) {
			model.addAttribute("msg", "Invalid login details. Please try again.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", "Invalid login details. Please try again.");
		}
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		if (u == null) {
			return new ModelAndView("login", "user", new User());
		} else {
			ingredients = recipeDaoImpl.getMyFridge(u);
			// getRecipeListFromIngredients();
			model.addAttribute("ingredients", ingredients);
			model.addAttribute("sessionUser", u);
			model.addAttribute("name", user.getLastName());
			return new ModelAndView("userHome");
		}
	}

	@RequestMapping(value = "/searchIngredients", method = RequestMethod.GET)
	public @ResponseBody String getIngredient(@RequestParam(value = "search") String search) {
		System.out.println("herreeee");
		try {
			HttpResponse<JsonNode> response = Unirest.get(
					"https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/ingredients/autocomplete?number=10&query="
							+ search + "&intolerances=egg")
					.header("X-Mashape-Key", "5GhPpodjI8mshvkAGPne57AcUdtBp1f85oDjsnQYBByQxzNsSS")
					.header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com").asJson();
			String jsonResponse = response.getBody().toString();
			JSONObject jsonObject = new JSONObject(jsonResponse.substring(1, jsonResponse.length() - 1));
			JSONArray jsonArray = new JSONArray(jsonResponse);
			List<String> temp = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				temp.add(jsonArray.getJSONObject(i).getString("name"));
				System.out.println(jsonArray.getJSONObject(i).getString("name"));
			}
			String result = temp.toString();
			return result.substring(1, result.length() - 1);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("should not come here...");
		return null;
	}

	@RequestMapping(value = "/generateRecipe", method = RequestMethod.GET)
	public @ResponseBody String generateRecipe(@RequestParam(value = "ingredients") String ing,
			@SessionAttribute("sessionUser") User user) {
		List<RecipeResponse> recipes = getRecipeListFromIngredients(ing);
		JSONArray j = new JSONArray(recipes);
		return j.toString();
	}

	@RequestMapping(value = "/getFavorites", method = RequestMethod.GET)
	public @ResponseBody String getFavorites(@SessionAttribute("sessionUser") User user) {
		List<RecipeResponse> recipes = new ArrayList<RecipeResponse>();
		List<String> favRecipeIds = recipeDaoImpl.getFavoriteRecipesIds(user);
		for (String recipeId : favRecipeIds) {

			RecipeResponse recipe = new RecipeResponse();
			try {
				HttpResponse<JsonNode> resp = Unirest
						.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + recipeId
								+ "/information")
						.header("X-Mashape-Key", "ROTbWuCYfNmshFsyL7i3BdpPT4wjp1E14wujsnUf8yGQkAjKu3")
						.header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com").asJson();

				HttpResponse<JsonNode> summaryResponse = Unirest
						.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + recipeId
								+ "/summary")
						.header("X-Mashape-Key", "ROTbWuCYfNmshFsyL7i3BdpPT4wjp1E14wujsnUf8yGQkAjKu3")
						.header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com").asJson();
				JSONObject jsObj = new JSONObject(resp.getBody().toString());
				JSONObject summaryJsObj = new JSONObject(summaryResponse.getBody().toString());
				recipe.setRecipeUrl(jsObj.getString("spoonacularSourceUrl"));
				recipe.setSummary(summaryJsObj.getString("summary"));
				recipe.setId(Integer.parseInt(recipeId));
				recipe.setImageUrl(jsObj.getString("image"));
				recipe.setTitle(jsObj.getString("title"));
				recipes.add(recipe);
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONArray j = new JSONArray(recipes);
		return j.toString();
	}

	@RequestMapping(value = "/addOrRemoveFavourite", method = RequestMethod.GET)
	public @ResponseBody String toggleFavourite(@RequestParam(value = "recipeId") String rid,
			@SessionAttribute("sessionUser") User user) {
		return recipeDaoImpl.toggleFavorite(user, rid);
	}

	private List<RecipeResponse> getRecipeListFromIngredients(String ingredients) {
		List<RecipeResponse> recipes = new ArrayList<RecipeResponse>();
		try {
			HttpResponse<JsonNode> response = Unirest
					.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/"
							+ "findByIngredients?number=5&ranking=1&ingredients=" + ingredients)
					.header("X-Mashape-Key", "ROTbWuCYfNmshFsyL7i3BdpPT4wjp1E14wujsnUf8yGQkAjKu3")
					.header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com").asJson();
			String jsonResponse = response.getBody().toString();
			JSONObject jsonObject = new JSONObject(jsonResponse.substring(1, jsonResponse.length() - 1));
			JSONArray jsonArray = new JSONArray(jsonResponse);
			for (int i = 0; i < jsonArray.length(); i++) {
				RecipeResponse recipe = new RecipeResponse();
				JSONObject object = jsonArray.getJSONObject(i);
				recipe.setId(object.getInt("id"));
				recipe.setImageType(object.getString("imageType"));
				recipe.setImageUrl(object.getString("image"));
				recipe.setLikes(object.getInt("likes"));
				recipe.setMissedIngredientCount(object.getInt("missedIngredientCount"));
				recipe.setTitle(object.getString("title"));
				HttpResponse<JsonNode> resp = Unirest
						.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + object.getInt("id")
								+ "/information")
						.header("X-Mashape-Key", "ROTbWuCYfNmshFsyL7i3BdpPT4wjp1E14wujsnUf8yGQkAjKu3")
						.header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com").asJson();
				HttpResponse<JsonNode> summaryResponse = Unirest
						.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + object.getInt("id")
								+ "/summary")
						.header("X-Mashape-Key", "ROTbWuCYfNmshFsyL7i3BdpPT4wjp1E14wujsnUf8yGQkAjKu3")
						.header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com").asJson();
				JSONObject jsObj = new JSONObject(resp.getBody().toString());
				JSONObject summaryJsObj = new JSONObject(summaryResponse.getBody().toString());
				recipe.setRecipeUrl(jsObj.getString("spoonacularSourceUrl"));
				recipe.setSummary(summaryJsObj.getString("summary"));
				recipe.setUsedIngredientCount(object.getInt("usedIngredientCount"));
				recipes.add(recipe);
			}
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(recipes.get(0).getRecipeUrl()+"----"+recipes.get(0).getTitle());
		return recipes;
	}
}
