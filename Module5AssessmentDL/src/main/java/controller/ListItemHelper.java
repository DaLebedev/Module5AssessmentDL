/**
 * Author: Daniel Lebedev - dalebedev 
 * CIS 175 - Spring 2024
 * Feb 1, 2024
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

public class ListItemHelper {

	static EntityManagerFactory emfactory = 
			Persistence.createEntityManagerFactory("Module3AssessmentDL");
	
	public void insertMovie(ListItem li) {
		
		// Creates EntityManager for database operations
		EntityManager em = emfactory.createEntityManager();
		
		// Starts database transaction
		em.getTransaction().begin();
		
		// Inserts/persists list item into databas
		em.persist(li);
		
		// Commits changes to database
		em.getTransaction().commit();
		
		// Closes EntityManager 
		em.close();
	
	}

	public void deleteMovie(ListItem toDelete) {
		
		// Creates EntityManager for database operations
		EntityManager em = emfactory.createEntityManager();
		
		// Starts database transaction
		em.getTransaction().begin();
		
		// Selects movies with title, director, and genre that match input
		TypedQuery<ListItem> typedQuery = em.createQuery
				("SELECT li FROM ListItem li WHERE li.title = :selectedTitle and"
						+ " li.director = :selectedDirector and"
						+ " li.genre = :selectedGenre", ListItem.class);
		
		// Substitutes parameters with actual data from the toDelete item
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedDirector", toDelete.getDirector());
		typedQuery.setParameter("selectedGenre", toDelete.getGenre());
		
		// Maximum of 1 result allowed
		typedQuery.setMaxResults(1);
		
		// Saves result in a list item
		ListItem result = typedQuery.getSingleResult();
		
		// Deletes the item
		em.remove(result);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateMovie(ListItem toEdit) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// Updates database with new movie details
		em.merge(toEdit);
		
		em.getTransaction().commit();
		em.close();
	}	
	
	public ListItem searchMovieById(int idToEdit) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		ListItem found = em.find(ListItem.class, idToEdit);
		
		em.close();
		return found;
	}

	public List<ListItem> searchMovieByTitle(String movieTitle) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// Queries for objects with title that matches the input title 
		TypedQuery<ListItem> typedQuery = em.createQuery
				("SELECT li FROM ListItem li WHERE li.title = :selectedTitle" , ListItem.class);
		typedQuery.setParameter("selectedTitle", movieTitle);
		
		List<ListItem> foundItems = typedQuery.getResultList();
		
		em.close();
		
		return foundItems;
	}

	public List<ListItem> searchMovieByDirector(String movieDirector) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// Queries for objects with director that matches the input director
		TypedQuery<ListItem> typedQuery = em.createQuery
				("SELECT li FROM ListItem li WHERE li.director = :selectedDirector" , ListItem.class);
		typedQuery.setParameter("selectedDirector", movieDirector);
		
		List<ListItem> foundItems = typedQuery.getResultList();
		
		em.close();
		
		return foundItems;
	}

	public List<ListItem> searchMovieByGenre(String movieGenre) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// Queries for objects with genre that matches the input genre
		TypedQuery<ListItem> typedQuery = em.createQuery
				("SELECT li FROM ListItem li WHERE li.director = :selectedDirector" , ListItem.class);
		typedQuery.setParameter("selectedDirector", movieGenre);
		
		List<ListItem> foundItems = typedQuery.getResultList();
		
		em.close();
		
		return foundItems;
	}
	
	public List<ListItem> showAllMovies() {
		
		System.out.println();
		
		// Queries all fields in table object
		EntityManager em = emfactory.createEntityManager();
	    List<ListItem> allItems = em.createQuery
	    		("SELECT li FROM ListItem li", ListItem.class).getResultList();
	    return allItems;
	}

	public void cleanUp() {
			emfactory.close();
	}
	
}
