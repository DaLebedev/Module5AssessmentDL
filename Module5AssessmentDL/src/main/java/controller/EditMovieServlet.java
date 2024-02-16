package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListItem;

/**
 * Servlet implementation class EditMovieServlet
 */
@WebServlet("/EditMovieServlet")
public class EditMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListItemHelper dao = new ListItemHelper();
		// Retrieves input from edit form
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		ListItem movieToUpdate = dao.searchMovieById(tempId);
		
		// Update movie with new information
		movieToUpdate.setTitle(title);
		movieToUpdate.setDirector(director);
		movieToUpdate.setGenre(genre);
		
		// Update corresponding record in movie table
		dao.updateMovie(movieToUpdate);
		
		// Forward to viewAllMoviesServlet
		getServletContext().getRequestDispatcher("/ViewAllMoviesServlet").forward(request, response);
	}

}
