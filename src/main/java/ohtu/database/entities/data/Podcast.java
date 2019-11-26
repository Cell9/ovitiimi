/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.database.entities.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Roni
 */
@Entity
public class Podcast extends AbstractPersistable<Long> {

	@Getter @Setter private String author;
	@Getter @Setter private String title;
	@Getter @Setter private String description;

	@OneToMany
	private List<Course> courses;
	
	@ElementCollection
	@CollectionTable(name = "podcast_tags")
	private List<String> tags;

    public Podcast() {
    	this.courses = new ArrayList<>();
    	this.tags = new ArrayList<>();
    }

    public Podcast(String author, String title, String description) {
    	this();
    	
        this.author = author;
        this.title = title;
        this.description = description;
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
