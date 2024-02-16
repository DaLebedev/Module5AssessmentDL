/**
 * Author: Daniel Lebedev - dalebedev 
 * Description: XXX
 * CIS 175 - Spring 2024
 * Feb 1, 2024
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOVIES")
public class ListItem {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DIRECTOR")
	private String director;
	
	@Column(name="GENRE")
	private String genre;
	
	// Constructors
	public ListItem() {
		super();
	}
	
	public ListItem(String title, String director, String genre) {
		super();
		this.title = title;
		this.director = director;
		this.genre = genre;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String returnMovieDetails() {
		return "Title: " + this.title + 
				", Director: " + this.director + 
				", Genre: " + this.genre;
	}
	
}
