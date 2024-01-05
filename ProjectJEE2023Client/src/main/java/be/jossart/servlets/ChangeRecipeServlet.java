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
 * Servlet implementation class ChangeRecipeServlet
 */
@WebServlet("/ChangeRecipeServlet")
public class ChangeRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idRecipe = Integer.parseInt(request.getParameter("idRecipe"));
        Recipe recipe = Recipe.find(idRecipe);
        List<Integer> idsRecipeIngredients = RecipeIngredient.findIds(idRecipe);
        List<Integer> idsRecipeSteps = RecipeStep.findIds(idRecipe);

        ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
        ArrayList<RecipeStep> recipeSteps = new ArrayList<>();

        for (int idRecipeIngredient : idsRecipeIngredients) {
            RecipeIngredient recipeIngredient = RecipeIngredient.find(idRecipe,idRecipeIngredient);

            Ingredient ingredient = Ingredient.find(recipeIngredient.getIdIngredient());
            recipeIngredient.setIngredient(ingredient);

            recipeIngredients.add(recipeIngredient);
        }

        for (int idRecipeStep : idsRecipeSteps) {
            RecipeStep recipeStep = RecipeStep.find(idRecipeStep);
            recipeSteps.add(recipeStep);
        }

        recipe.setRecipeIngredientList(recipeIngredients);
        recipe.setRecipeStepList(recipeSteps);
        request.setAttribute("recipe", recipe);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ChangeRecipe.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("person") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login page if not logged in
            return;
        }

        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String recipeName = request.getParameter("recipeName");
        RecipeGender recipeGender = RecipeGender.valueOf(request.getParameter("recipeGender"));
        
        Person person = (Person) session.getAttribute("person");
        
        Recipe updatedRecipe = new Recipe(recipeId, recipeName, person, recipeGender, null, null);
        boolean recipeUpdated = updatedRecipe.update();

        if (recipeUpdated) {
            String[] ingredientNames = request.getParameterValues("ingredientName");
            String[] ingredientTypes = request.getParameterValues("ingredientType");
            String[] ingredientQuantities = request.getParameterValues("ingredientQuantity");

            for (int i = 0; i < ingredientNames.length; i++) {
                String ingredientName = ingredientNames[i];
                IngredientType ingredientType = IngredientType.valueOf(ingredientTypes[i]);
                double ingredientQuantity = Double.parseDouble(ingredientQuantities[i]);

                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientName);
                ingredient.setType(ingredientType);
                ingredient.create();
                ingredient = Ingredient.findId(ingredient);

                RecipeIngredient recipeIngredient = new RecipeIngredient(recipeId, ingredient.getIdIngredient(), ingredientQuantity, ingredient, updatedRecipe);
                recipeIngredient.update();
            }

            String[] stepInstructions = request.getParameterValues("stepInstruction");

            for (String stepInstruction : stepInstructions) {
                RecipeStep recipeStep = new RecipeStep(0, stepInstruction, updatedRecipe);
                recipeStep.update();
            }
            request.setAttribute("success", "Recipe updated successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("fail", "Failed to update the recipe. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
        }
    }
}
