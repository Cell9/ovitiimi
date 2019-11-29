package ohtu.database.entities.recommendations;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import lombok.Data;
import ohtu.database.entities.data.Course;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Recommendation extends AbstractPersistable<Long> {

    @Id
    private Long id;

    @ManyToMany
    protected List<Course> courses = new ArrayList<Course>();
    @ElementCollection
    protected List<String> tags = new ArrayList<String>();
    protected String title;

    public abstract String getTitle();
    public abstract void setTitle(String title);
    public abstract void addCourse(Course course);

    public abstract RecommendationType getType();

    @Override
    public String toString() {
        return this.getTitle();
    }
}
