package be.jossart.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.jossart.javabeans.Recipe;

public class RecipeDAO extends DAO<Recipe> {
	public RecipeDAO() {
		
	}
	@Override
	public boolean create(Recipe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Recipe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Recipe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Recipe find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Recipe> findAll(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Recipe> findRecipe(String recherche){
		String responseJSON = this.resource.path("recipe").path(String.valueOf(recherche)).accept(MediaType.APPLICATION_JSON).get(String.class);
		List<Recipe> recipes = new ArrayList<Recipe>();
		JSONArray array = new JSONArray(responseJSON);
		ObjectMapper mapper = new ObjectMapper();
		for(int i =0;i<array.length();i++) {
			String personJSON = array.get(i).toString();
			try {
				recipes.add(mapper.readValue(personJSON,Recipe.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recipes;
	}

}
