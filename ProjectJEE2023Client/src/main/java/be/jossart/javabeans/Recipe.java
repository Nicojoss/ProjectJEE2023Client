package be.jossart.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Recipe implements Serializable{

	//ATTRIBUTES
	private static final long serialVersionUID = -7287441285222249732L;
	private int idRecipe;
	private String name;
	private Person person;
	private RecipeGender recipeGender;
	private ArrayList<RecipeIngredient> recipeIngredientList;
	private ArrayList<RecipeStep> recipeStepList;
	
	//CTOR
	public Recipe() { // Je les aies mis si on en a pas besoin on supprime à la fin
		recipeIngredientList = new ArrayList<>();
		recipeStepList = new ArrayList<>();
	}
	public Recipe(int idRecipe, String name, Person person, RecipeGender recipeGender,
			ArrayList<RecipeIngredient> recipeIngredientList
			, ArrayList<RecipeStep> recipeStepList) {
		super();
		this.idRecipe = idRecipe;
		this.name = name;
		this.person = person;
		this.recipeGender = recipeGender;
		this.recipeIngredientList = recipeIngredientList;
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
	public ArrayList<RecipeIngredient> getRecipeIngredientList() {
		return recipeIngredientList;
	}
	public void setRecipeIngredientList(ArrayList<RecipeIngredient> recipeIngredientList) {
		this.recipeIngredientList = getRecipeIngredientList();
	}
	public ArrayList<RecipeStep> getRecipeStepList() {
		return recipeStepList;
	}
	public void setRecipeStepList(ArrayList<RecipeStep> recipeStepList) {
		this.recipeStepList = recipeStepList;
	}
	@Override
	public String toString() {
		return "Recipe [idRecipe=" + idRecipe + ", name=" + name + ", person=" + person + ", recipeGender="
				+ recipeGender + ", recipeIngredientList=" + recipeIngredientList + ", recipeStepList=" + recipeStepList + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRecipe, recipeIngredientList, name, person, recipeGender, recipeStepList);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return idRecipe == other.idRecipe && Objects.equals(recipeIngredientList, other.recipeIngredientList)
				&& Objects.equals(name, other.name) && Objects.equals(person, other.person)
				&& recipeGender == other.recipeGender && Objects.equals(recipeStepList, other.recipeStepList);
	}
}
