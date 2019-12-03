package ohtu.database.entities.recommendations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import ohtu.database.entities.data.Course;

@Entity
@Data
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Recommendation extends AbstractPersistable<Long> {

    @NotEmpty
    protected String title;

    @ManyToMany
    protected List<Course> courses = new ArrayList<Course>();

    @ElementCollection
    protected List<String> tags = new ArrayList<String>();

    public Recommendation() {
    }

    public Recommendation(String title, List<Course> courses, List<String> tags) {
        this.title = title;
        this.courses = courses;
        this.tags = tags;
    }
    
    public void setTags(List<String> tags) {
    	//Filter out duplicates
    	//Spring includes empty strings, so filter them out. Does Spring include this functionality somehow?
    	this.tags = tags.stream().distinct().filter((s) -> s != null && !s.isEmpty()).collect(Collectors.toList());
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public abstract RecommendationType getType();

    @Override
    public String toString() {
        return this.getTitle();
    }
}
