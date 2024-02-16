package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewAllMoviesServlet
 */
@WebServlet("/ViewAllMoviesServlet")
public class ViewAllMoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllMoviesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ListItemHelper dao = new ListItemHelper();
		request.setAttribute("allItems", dao.showAllMovies());
		
		// Default path
		String path = "/movie-catalog.jsp";
		
		// If movie table is empty return to index page
		if (dao.showAllMovies().isEmpty()) {
			path = "index.html";
		}
		
		// Forwards to movie-catalog or back to index
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
