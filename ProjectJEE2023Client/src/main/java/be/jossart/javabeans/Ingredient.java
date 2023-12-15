package be.jossart.javabeans;

import java.io.Serializable;

public class Ingredient implements Serializable{
	//ATTRIBUTES
	private static final long serialVersionUID = 634419802561898936L;
	private int idIngredient;
	private String name;
	private IngredientType type;
	private Recipe recipe;
	//CTOR
	public Ingredient() {
	}
	public Ingredient(int idIngredient, String name, IngredientType type, Recipe recipe) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.type = type;
		this.recipe = recipe;
	}
	//METHODS
	//GETTERS SETTERS
	public int getIdIngredient() {
		return idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IngredientType getType() {
		return type;
	}
	public void setType(IngredientType type) {
		this.type = type;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}
