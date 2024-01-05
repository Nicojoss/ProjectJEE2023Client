package be.jossart.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeIngredient;
import be.jossart.javabeans.RecipeStep;

/**
 * Servlet implementation class RemoveRecipeServlet
 */
@WebServlet("/RemoveRecipeServlet")
public class RemoveRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idRecipe = Integer.parseInt(request.getParameter("idRecipe"));

        List<Integer> recipeStepListIds = RecipeStep.findIds(idRecipe);
        List<Integer> recipeIngredientListIds = RecipeIngredient.findIds(idRecipe);

        for (int recipeStepId : recipeStepListIds) {
        	RecipeStep recipeStep = new RecipeStep(recipeStepId,null,null);
            recipeStep.delete();
        }

        for (int recipeIngredientId : recipeIngredientListIds) {
        	RecipeIngredient recipeIngredient = new RecipeIngredient(idRecipe,recipeIngredientId,null,null);
            recipeIngredient.delete();
        }
        
        Recipe recipe = new Recipe(idRecipe,null,null,null,null,null);
        boolean isRecipeDeleted = recipe.delete();
        if (isRecipeDeleted) {
            response.sendRedirect(request.getContextPath() + "/Home.jsp?deleteStatus=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/Home.jsp?deleteStatus=failed");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
