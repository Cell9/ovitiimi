package ohtu.database.entities.data;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course extends AbstractPersistable<Long> {
    @Getter @Setter private String code;
    @Getter @Setter private String name;

    @Override
    public String toString() {
        return code + " " + name;
    }
}
