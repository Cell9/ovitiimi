package ohtu.database.entities.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

// Roni
//Toteuttaa videot ja blogit
@Entity
@Data
public class Link extends AbstractPersistable<Long> {
    @Id
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String url;

    @ManyToMany
    private List<Course> courses;

    @ElementCollection
    @CollectionTable(name = "link_tags")
    private List<String> tags;

    public Link() {
        this.courses = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public Link(String title, String url) {
        this();

        this.title = title;
        this.url = url;
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
