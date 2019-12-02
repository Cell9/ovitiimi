package ohtu.database.entities.recommendations;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ohtu.database.entities.data.Course;
//import ohtu.database.entities.data.Podcast;

@Entity
@DiscriminatorValue("podcast")
public class PodcastRecommendation extends Recommendation {

    @Id
    private Long id;
    @NotEmpty
    @Getter @Setter private String author;
    @NotEmpty
    @Getter @Setter private String url;
    @NotEmpty
    @Getter @Setter private String description;
    
    public PodcastRecommendation() {
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.PODCAST;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.getTitle(), this.getAuthor(), this.getDescription());
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
