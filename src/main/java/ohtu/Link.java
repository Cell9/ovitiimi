package ohtu;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

// Roni
//Toteuttaa videot ja blogit
@Entity
public class Link extends AbstractPersistable<Long> {

    private String title;
    private String url;
    private ArrayList<String> tags;
    private ArrayList<String> courses;

    public Link() {
        tags = new ArrayList<>();
        courses =  new ArrayList<>();
    }

    public Link(String title, String url, ArrayList<String> tags, ArrayList<String> courses) {
        this.title = title;
        this.url = url;
        this.tags = tags;
        this.courses = courses;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    void addCourse(String course) {
        this.courses.add(course);
    }

    void addTag(String tag) {
        this.tags.add(tag);
    }
}
