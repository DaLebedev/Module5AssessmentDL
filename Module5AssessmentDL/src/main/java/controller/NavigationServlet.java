package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListItem;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/NavigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ListItemHelper dao = new ListItemHelper();
		// Retrieve which button was pressed
		String act = request.getParameter("doThisToMovie");
		String path = "/ViewAllMoviesServlet";

		if (act.equals("Delete Movie")) {
			try {
				// Select movie to delete depending on which movie radio button was selected
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListItem movieToDelete = dao.searchMovieById(tempId);
				// Delete movie from table
				dao.deleteMovie(movieToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an item");
			}
		} else if (act.equals("Edit Movie")) {
			try {
				// Select movie to edit depending on which movie radio button was selected
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListItem movieToEdit = dao.searchMovieById(tempId);
				request.setAttribute("movieToEdit", movieToEdit);
				// Sets path to edit movie form
				path = "/edit-movie.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an item");
			}
		} else if (act.equals("Add Movie")) {
			// Sets path to index page with add movie form
			path = "/index.html";
		}

		// Forwards to edit-movie.jsp or back to index
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
