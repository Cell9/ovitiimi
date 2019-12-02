package ohtu.database.entities.recommendations;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import ohtu.database.entities.data.Course;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Recommendation extends AbstractPersistable<Long> {

    @ManyToMany
    protected List<Course> courses = new ArrayList<Course>();
    @ElementCollection
    protected List<String> tags = new ArrayList<String>();
    @NotEmpty
    protected String title;

    public abstract RecommendationType getType();

    @Override
    public String toString() {
        return this.getTitle();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
