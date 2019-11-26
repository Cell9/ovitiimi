package ohtu.database.entities.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course extends AbstractPersistable<Long> {
	
	@Column(name = "code", unique = true)
	@NotEmpty
    @Getter @Setter private String code;
	
	@NotEmpty
    @Getter @Setter private String name;

    @Override
    public String toString() {
        return code + " " + name;
    }
}
