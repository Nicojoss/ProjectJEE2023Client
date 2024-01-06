package be.jossart.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.jossart.javabeans.Ingredient;
import be.jossart.javabeans.IngredientType;
import be.jossart.javabeans.Person;
import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeGender;
import be.jossart.javabeans.RecipeIngredient;
import be.jossart.javabeans.RecipeStep;

/**
 * Servlet implementation class CreateRecipeServlet
 */
@WebServlet("/CreateRecipeServlet")
public class CreateRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		    HttpSession session = request.getSession(false);
		    Person person = (Person) session.getAttribute("person");
	        if (session == null || person == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }
	        String name = request.getParameter("name");
	        String gender = request.getParameter("gender");
	        String[] ingredientNames = request.getParameterValues("ingredientNames");
	        String[] ingredientTypes = request.getParameterValues("ingredientTypes");
	        String[] quantities = request.getParameterValues("quantities");
	        String[] instructions = request.getParameterValues("instructions");
	        if(name == null || gender == null|| ingredientNames == null || 
	        		ingredientTypes == null || quantities == null ||
	        		instructions == null)
	        {
	        	request.setAttribute("fail", "Failed to create the recipe. Please try again.");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
	            dispatcher.forward(request, response);
	        }
	        RecipeGender recipeGender = RecipeGender.valueOf(gender);
	        List<Integer> ingredientIds = new ArrayList<>();
	        for (int i = 0; i < ingredientNames.length; i++) {
	            Ingredient ingredient = new Ingredient();
	            ingredient.setName(ingredientNames[i]);
	            ingredient.setType(IngredientType.valueOf(ingredientTypes[i]));

	            if (ingredient.create()) {
	                Ingredient foundIngredient = Ingredient.findId(ingredient);
	                ingredientIds.add(foundIngredient.getIdIngredient());
	            } else {
	                response.sendRedirect("recipeCreationError.jsp");
	                return;
	            }
	        }
	        Recipe recipe = new Recipe();
	        recipe.setName(name);
	        recipe.setRecipeGender(recipeGender);
	        recipe.setPerson(person);

	        if (recipe.create()) {
	            Recipe foundRecipe = Recipe.findId(recipe);

	            for (int i = 0; i < ingredientIds.size(); i++) {
	                RecipeIngredient recipeIngredient = new RecipeIngredient();
	                recipeIngredient.setIdRecipe(foundRecipe.getIdRecipe());
	                recipeIngredient.setIdIngredient(ingredientIds.get(i));
	                recipeIngredient.setQuantity(Double.parseDouble(quantities[i]));
	                recipeIngredient.create();
	            }
	            for (String instruction : instructions) {
	                RecipeStep recipeStep = new RecipeStep();
	                recipeStep.setInstruction(instruction);
	                recipeStep.setRecipe(foundRecipe);
	                recipeStep.create();
	            }
	            request.setAttribute("success", "Recipe created successfully!");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
	            dispatcher.forward(request, response);
	        } else {
	            request.setAttribute("fail", "Failed to create the recipe. Please try again.");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
	            dispatcher.forward(request, response);
	        }
	 }
}
