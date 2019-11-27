package ohtu.database.entities.recommendations;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Recommendation extends AbstractPersistable<Long> {
	
    public abstract String getTitle();
    public abstract RecommendationType getType();
    
    @Override
    public String toString() {
    	return this.getTitle();
    }
}
