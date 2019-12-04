package ohtu.database.entities.recommendations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import ohtu.database.entities.data.Course;

@Entity
@Data
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Recommendation extends AbstractPersistable<Long> {

    @NotEmpty
    protected String title;
    
    private Timestamp readTime;

    @ManyToMany
    @MapKey(name = "id")
    protected Map<Long, Course> courses = new HashMap<>();

    @ElementCollection
    protected List<String> tags = new ArrayList<String>();

    public Recommendation() {
    }

    public Recommendation(String title, Map<Long, Course> courses, List<String> tags) {
        this.title = title;
        this.courses = courses;
        this.tags = tags;
    }
    
    public void setCourses(Collection<Course> courses) {
    	this.courses = courses.stream().collect(Collectors.toMap((o) -> o.getId(), (o) -> o));
    }
    
    public void setTags(List<String> tags) {
    	//Filter out duplicates
    	//Spring includes empty strings, so filter them out. Does Spring include this functionality somehow?
    	this.tags = tags.stream().distinct().filter((s) -> s != null && !s.isEmpty()).collect(Collectors.toList());
    }

    public void addCourse(Course course) {
        this.courses.put(course.getId(), course);
    }
    
    public boolean hasCourse(Course course) {
    	return this.courses.containsKey(course.getId());
    }
    
    public boolean hasCourseById(Long id) {
    	return this.courses.containsKey(id);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public abstract RecommendationType getType();
    
    public Collection<Course> getCourses() {
    	return this.courses.values();
    }
    
    public String coursesAsString() {
    	if (this.courses.isEmpty()) { //Fast path
    		return "";
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	Iterator<Course> courses = this.courses.values().iterator();
    	while (true) {
    		Course next = courses.next();
    		
    		sb.append(next);
    		
    		if (courses.hasNext()) {
    			sb.append(", ");
    		} else {
    			break;
    		}
    	}
    	
    	return sb.toString();
    }
    
    public String tagsAsString() {
    	return String.join(", ", this.tags);
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
