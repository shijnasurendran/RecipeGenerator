package com.recipe.generator.model;

public class RecipeResponse {

	String title, imageUrl, imageType, recipeUrl, summary;
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRecipeUrl() {
		return recipeUrl;
	}
	public void setRecipeUrl(String recipeUrl) {
		this.recipeUrl = recipeUrl;
	}
	int id, usedIngredientCount, missedIngredientCount, likes;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public int getUsedIngredientCount() {
		return usedIngredientCount;
	}
	public void setUsedIngredientCount(int usedIngredientCount) {
		this.usedIngredientCount = usedIngredientCount;
	}
	public int getMissedIngredientCount() {
		return missedIngredientCount;
	}
	public void setMissedIngredientCount(int missedIngredientCount) {
		this.missedIngredientCount = missedIngredientCount;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
}
