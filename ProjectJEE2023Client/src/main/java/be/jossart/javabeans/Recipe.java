package be.jossart.javabeans;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable{

	//ATTRIBUTES
	private static final long serialVersionUID = -7287441285222249732L;
	private int idRecipe;
	private String name;
	private Person person;
	private RecipeGender recipeGender;
	private ArrayList<Ingredient> ingredientList;
	private ArrayList<RecipeStep> recipeStepList;
	
	//CTOR
	public Recipe() { // Je les aies mis si on en a pas besoin on supprime à la fin
		ingredientList = new ArrayList<>();
		recipeStepList = new ArrayList<>();
	}
	public Recipe(int idRecipe, String name, Person person, RecipeGender recipeGender,
			ArrayList<Ingredient> ingredientList, ArrayList<RecipeStep> recipeStepList) {
		super();
		this.idRecipe = idRecipe;
		this.name = name;
		this.person = person;
		this.recipeGender = recipeGender;
		this.ingredientList = ingredientList;
		this.recipeStepList = recipeStepList;
	}
	//METHODS

	//GETTERS SETTERS
	public int getIdRecipe() {
		return idRecipe;
	}
	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public RecipeGender getRecipeGender() {
		return recipeGender;
	}
	public void setRecipeGender(RecipeGender recipeGender) {
		this.recipeGender = recipeGender;
	}
	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(ArrayList<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}
	public ArrayList<RecipeStep> getRecipeStepList() {
		return recipeStepList;
	}
	public void setRecipeStepList(ArrayList<RecipeStep> recipeStepList) {
		this.recipeStepList = recipeStepList;
	}
}
