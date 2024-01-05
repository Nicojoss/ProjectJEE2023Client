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

import be.jossart.dao.RecipeDAO;
import be.jossart.javabeans.Recipe;

/**
 * Servlet implementation class ConsultOwnRecipeServlet
 */
@WebServlet("/ConsultOwnRecipeServlet")
public class ConsultOwnRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultOwnRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int idPerson = (int) session.getAttribute("idPerson");

        RecipeDAO recipeDAO = new RecipeDAO();
        List<Integer> recipeIds = recipeDAO.findIds(idPerson);

        ArrayList<Recipe> recipes = new ArrayList<>();

        for (int idRecipe : recipeIds) {
            Recipe recipe = recipeDAO.find(idRecipe);
            if (recipe != null) {
                recipes.add(recipe);
            }
        }
        request.setAttribute("recipes", recipes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ConsultOwnRecipe.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
