/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Roni
 */
@Entity
public class Podcast extends AbstractPersistable<Long> {

    private String author;
    private String title;
    private String description;
    private ArrayList<String> tags;
    private ArrayList<String> courses;

    public Podcast() {
        tags = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public Podcast(String author, String title, String description, ArrayList<String> tags, ArrayList<String> courses) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.courses = courses;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void addCourse(String course) {
        this.courses.add(course);
    }

}
