/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.database.entities.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Roni
 */
@Entity
public class Book extends AbstractPersistable<Long> {

	@NotEmpty
	@Getter @Setter private String title;

	@NotEmpty
	@Getter @Setter private String author;

	@Column(name = "isbn", unique = true) //NULL's aren't indexed
	@ISBN
	@Getter @Setter private String isbn;
	
	@ManyToMany
	private List<Course> courses;
	
	@ElementCollection
	@CollectionTable(name = "book_tags")
	private List<String> tags;

    public Book() {
    	this.courses = new ArrayList<>();
    	this.tags = new ArrayList<>();
    }

    public Book(String title, String author, String isbn) {
    	this();
    	
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    
    public void addCourse(Course course) {
    	this.courses.add(course);
    }
    
    public void addTag(String tag) {
    	this.tags.add(tag);
    }
    
    public List<Course> getCourses() {
    	return this.courses;
    }
}
