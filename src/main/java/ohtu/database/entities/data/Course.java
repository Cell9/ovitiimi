package ohtu.database.entities.data;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ohtu.database.entities.recommendations.BookRecommendation;
import ohtu.database.entities.recommendations.PodcastRecommendation;
import ohtu.database.entities.recommendations.Recommendation;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course extends AbstractPersistable<Long> {

    @Id
    private Long id;

    @Column(name = "code", unique = true)
    @NotEmpty
    @Getter
    @Setter
    private String code;

    @NotEmpty
    @Getter
    @Setter
    private String name;

    @ManyToMany(targetEntity=Recommendation.class, mappedBy = "courses", fetch=FetchType.EAGER)
    private List<Recommendation> recommendations;

    @Override
    public String toString() {
        return code + " " + name;
    }
}
