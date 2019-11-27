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
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Roni
 */
@Entity
public class Podcast extends AbstractPersistable<Long> {

    @NotEmpty
    @Getter @Setter private String author;
    
    @NotEmpty
    @Getter @Setter private String title;
    
    @Getter @Setter private String description;

    @ManyToMany
    private List<Course> courses;

    @ElementCollection
    @CollectionTable(name = "podcast_tags")
    private List<String> tags;

    public Podcast() {
    	this.courses = new ArrayList<>();
    	this.tags = new ArrayList<>();
    }

    public Podcast(String title, String author, String description) {
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
