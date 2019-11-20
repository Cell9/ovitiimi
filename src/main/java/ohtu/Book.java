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
public class Book extends AbstractPersistable<Long> {
    
    private String author;
    private String title;
    private String ISBN;
    private ArrayList<String> tags;
    private ArrayList<String> courses;

    public Book() {
    }

    public Book(String author, String title, String ISBN, ArrayList<String> tags, ArrayList<String> courses) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.tags = tags;
        this.courses = courses;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getCourses() {
        return courses;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }   
}
