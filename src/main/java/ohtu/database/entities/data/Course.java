package ohtu.database.entities.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import ohtu.database.entities.ISimilar;
import ohtu.database.entities.recommendations.Recommendation;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course extends AbstractPersistable<Long> implements ISimilar<Course> {

    @Column(name = "code", unique = true)
    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @ManyToMany(targetEntity=Recommendation.class, mappedBy = "courses", fetch=FetchType.EAGER)
    private List<Recommendation> recommendations;

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.name, this.code);
    }
    
    public boolean equals(Object o) {
    	if (this == o) {
    		return true;
    	}
    	
    	if (!(o instanceof Course)) {
    		return false;
    	}
    	
    	Course other = (Course)o;
    	
    	return this.getId().equals(other.getId());
    }
    
    public boolean areSimilar(Course other) {
    	if (this == other) {
    		return true;
    	}
    	
    	return this.code.equals(other.code) && this.name.equals(other.name);
    }
    
    public int hashCode() {
    	return this.getId().hashCode();
    }
}
