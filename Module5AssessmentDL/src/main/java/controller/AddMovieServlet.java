package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListItem;

/**
 * Servlet implementation class AddMovieServlet
 */
@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddMovieServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve input variables from form
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");
		
		// Create new movie object
		ListItem li = new ListItem(title, director, genre);
		
		// Adds movie to SQL table using helper class
		ListItemHelper dao = new ListItemHelper();
		dao.insertMovie(li);
		
		// Forwards to index.html
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		
	}

}
