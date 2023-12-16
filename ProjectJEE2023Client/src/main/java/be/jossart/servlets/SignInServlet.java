package be.jossart.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.jossart.javabeans.Person;

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/SignIn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String firstnameParam = request.getParameter("firstname");
		String lastnameParam = request.getParameter("lastname");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		
		if(request.getParameter("submit") != null) {
			if(!parametersAreValid(firstnameParam, lastnameParam, usernameParam, passwordParam)) {
				request.setAttribute("error", "You must enter at least 3 characters for the first name and last name and 5 characters for the username and password");
			 	getServletContext().getRequestDispatcher("/WEB-INF/JSP/SignIn.jsp").forward(request, response);
			}else {
				Person person = new Person(firstnameParam, lastnameParam, usernameParam, passwordParam);
				
				if(person.insertPerson()) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogInServlet.jsp").forward(request, response);
				}else {
					request.setAttribute("error", "An error during the register ! Go back to <a href=\"/ProjectJEE2023Client/SignInServlet\">Sign In Page</a>");
				 	getServletContext().getRequestDispatcher("/WEB-INF/ErrorsJSP/Error.jsp").forward(request, response);
				}
			}
		}
	}
	public boolean parametersAreValid(String firstnameParam, String lastnameParam, String usernameParam, String passwordParam) {
		if(firstnameParam == null || firstnameParam.equals("") || firstnameParam.length() < 3
				|| lastnameParam == null || lastnameParam.equals("") || lastnameParam.length() < 3
				|| usernameParam == null || usernameParam.equals("") || usernameParam.length() < 5
				|| passwordParam == null || passwordParam.equals("") || passwordParam.length() < 5) {
			return false;
		}
		return true;
	}

}
