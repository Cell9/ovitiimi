package ohtu.database.entities.data;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohtu.database.entities.recommendations.Recommendation;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course extends AbstractPersistable<Long> {

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
        return code + " " + name;
    }
}
