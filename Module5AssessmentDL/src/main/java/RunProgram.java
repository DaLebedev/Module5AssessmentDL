import java.util.List;
import java.util.Scanner;
import controller.ListItemHelper;
import model.ListItem;

/**
 * Author: Daniel Lebedev - dalebedev 
 * CIS 175 - Spring 2024
 * Feb 1, 2024
 */

public class RunProgram {

	static Scanner in = new Scanner(System.in);
	static ListItemHelper lih = new ListItemHelper();

	private static void displayOptions() {
		
		boolean goAgain = true;
		System.out.println("Welcome to Daniel's Movie Catalog!");
		while (goAgain) {
			
			System.out.println("\n*  Select an option:");
			System.out.println("*  1 - Add a Movie");
			System.out.println("*  2 - Edit a Movie");
			System.out.println("*  3 - Delete a Movie");
			System.out.println("*  4 - View List of Movies");
			System.out.println("*  5 - Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			
			try {
				if (selection == 1) {
					addItem();
				} else if (selection == 2) {
					editItem();
				} else if (selection == 3) {
					deleteItem();
				} else if (selection == 4) {
					viewList();
				} else if (selection == 5) {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				} else {
					
					// Throws error if input is invalid
					throw new IllegalArgumentException("Error: Invalid selection!");
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
	}
	
	private static void addItem() {
		
		System.out.print("Enter the movie title: ");
		String title = in.nextLine();
		System.out.print("Enter the director: ");
		String director = in.nextLine();
		System.out.print("Enter the movie genre: ");
		String genre = in.nextLine();
		
		// Creates item as object and passes it to be inserted into the database
		ListItem toAdd = new ListItem(title, director, genre);
		lih.insertMovie(toAdd);
	}
	
	private static void deleteItem() {
		System.out.print("Enter the title of the movie you wish to delete: ");
		String title = in.nextLine();
		System.out.print("Enter the director of the movie you wish to delete: ");
		String director = in.nextLine();
		System.out.print("Enter the movie genre of the movie you wish to delete: ");
		String genre = in.nextLine();
		
		// Creates item with inputted information and passes it to be deleted
		ListItem toDelete = new ListItem(title, director, genre);
		lih.deleteMovie(toDelete);
		
	}
	
	private static void editItem() {
		
		System.out.println("\nHow would you like to search? ");
		System.out.println("1 - Search by Title");
		System.out.println("2 - Search by Director");
		System.out.println("3 - Search by Genre");
		
		int searchBy = in.nextInt();
		in.nextLine();
		
		List<ListItem> foundItems = null;
		
		try {
			if (searchBy == 1) {
				System.out.print("\nEnter the movie title: ");
				String movieTitle = in.nextLine();
				foundItems = lih.searchMovieByTitle(movieTitle);
				
			} else if (searchBy == 2) {
				System.out.print("\nEnter the director name: ");
				String movieDirector = in.nextLine();
				foundItems = lih.searchMovieByDirector(movieDirector);
				
			} else if (searchBy == 3){
				System.out.print("\nEnter the genre: ");
				String movieGenre = in.nextLine();
				foundItems = lih.searchMovieByGenre(movieGenre);
			} else {
				
				// Throws error if input is invalid
				throw new IllegalArgumentException("Error: Invalid selection!");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + "\n");
		}
		
		if ( foundItems != null && !foundItems.isEmpty()) {
			
			System.out.println("Found Results");
			for (ListItem l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListItem toEdit = lih.searchMovieById(idToEdit);
			System.out.println("Movie Title: " + toEdit.getTitle() + " Directed by: " + toEdit.getDirector() + ", Genre: ");
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Director");
			System.out.println("3 : Update Genre");
			int update = in.nextInt();
			in.nextLine();
			
			try {
				if (update == 1) {
					System.out.print("New Movie Title: ");
					String newTitle = in.nextLine();
					toEdit.setTitle(newTitle);
					
				} else if (update == 2) {
					System.out.print("New Director: ");
					String newDirector = in.nextLine();
					toEdit.setDirector(newDirector);
					
				} else if (update == 3) {
					System.out.print("New Genre: ");
					String newGenre = in.nextLine();
					toEdit.setGenre(newGenre);
				} else {
					
					// Throws error if input is invalid
					throw new IllegalArgumentException("Error: Invalid selection!");
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage() + "\n");
			}
			
			lih.updateMovie(toEdit);
			
		} else {
			System.out.println("No matching movies found.");
		}
	}
	

	private static void viewList() {
		// Prints all movies in table object
		List<ListItem> allItems = lih.showAllMovies();
		for(ListItem singleItem : allItems) {
			System.out.println(singleItem.returnMovieDetails());
		}

	}
	
	public static void main(String[] args) {
		displayOptions();
	}






	
}
