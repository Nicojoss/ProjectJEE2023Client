package be.jossart.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.jossart.javabeans.Person;
import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeGender;

public class RecipeDAO extends DAO<Recipe> {
    public RecipeDAO() {
    }
    @Override
    public boolean create(Recipe obj) {
        MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
        paramsPost.add("name", obj.getName());
        paramsPost.add("gender", obj.getRecipeGender().toString());
        paramsPost.add("idPerson", Integer.toString(obj.getPerson().getIdPerson()));

        try {
            ClientResponse res = this.resource
                    .path("recipe/create")
                    .accept(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, paramsPost);

            return res.getStatus() == 201;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Recipe obj) {
        try {
            ClientResponse res = this.resource
                    .path("recipe/delete/" + obj.getIdRecipe())
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class);

            return res.getStatus() == 204;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Recipe obj) {
        MultivaluedMap<String, String> paramsPut = new MultivaluedMapImpl();
        paramsPut.add("name", obj.getName());
        paramsPut.add("gender", obj.getRecipeGender().toString());
        paramsPut.add("idPerson", Integer.toString(obj.getPerson().getIdPerson()));

        try {
            ClientResponse res = this.resource
                    .path("recipe/update/" + obj.getIdRecipe())
                    .accept(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, paramsPut);

            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Recipe find(int id) {
        try {
            ClientResponse res = this.resource
                    .path("recipe/get/" + id)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (res.getStatus() == 200) {
                String response = res.getEntity(String.class);
                JSONObject json = new JSONObject(response);

                int recipeId = json.getInt("idRecipe");
                String name = json.getString("name");
                String gender = json.getString("recipeGender");
                int idPerson = json.getInt("idPerson");

                Person person = new Person(idPerson, null, null, null, null);
                RecipeGender recipeGender = RecipeGender.valueOf(gender);

                Recipe recipe = new Recipe(recipeId, name, person, recipeGender, null, null);

                return recipe;
            } else if (res.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe. Status: " + res.getStatus());
                return null;
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Recipe> findAll(Object obj) {
    	// TODO Auto-generated method stub
        return null;
    }

    public Recipe findId(Recipe recipe) {
        try {
            String name = recipe.getName();
            String gender = recipe.getRecipeGender().toString();
            int idPerson = recipe.getPerson().getIdPerson();

            ClientResponse response = this.resource
                    .path("recipe/getId/" + name + "/" + gender + "/" + idPerson)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONObject json = new JSONObject(responseJson);

                int recipeId = json.getInt("idRecipe");
                String retrievedName = json.getString("name");
                String retrievedGender = json.getString("recipeGender");
                int retrievedIdPerson = json.getInt("idPerson");

                Person retrievedPerson = new Person(retrievedIdPerson, null, null, null, null);
                RecipeGender retrievedRecipeGender = RecipeGender.valueOf(retrievedGender);

                return new Recipe(recipeId, retrievedName, retrievedPerson, retrievedRecipeGender, null, null);
            } else if (response.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe. Status: " + response.getStatus());
                return null;
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Integer> findIds(int idPerson) {
        try {
            ClientResponse response = this.resource
                    .path("recipe/findIds/" + idPerson)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONArray jsonArray = new JSONArray(responseJson);

                List<Integer> recipeIds = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    recipeIds.add(jsonArray.getInt(i));
                }

                return recipeIds;
            } else if (response.getStatus() == 404) {
                return Collections.emptyList();
            } else {
                System.out.println("Failed to retrieve recipe IDs. Status: " + response.getStatus());
                return Collections.emptyList();
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return Collections.emptyList();
        }
    }
}
