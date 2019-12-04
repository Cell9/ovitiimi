package ohtu.database.entities.recommendations;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import ohtu.database.entities.data.Course;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue("podcast")
public class PodcastRecommendation extends Recommendation {

    @NotEmpty
    private String author;

    @NotEmpty
    private String url;

    @NotEmpty
    private String description;

    public PodcastRecommendation(String title, List<Course> courses, List<String> tags, String author, String url, String description) {
        super(title, courses, tags);
        this.author = author;
        this.url = url;
        this.description = description;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.PODCAST;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", this.getTitle(), this.getAuthor(), this.getUrl(), this.getDescription());
    }
}
