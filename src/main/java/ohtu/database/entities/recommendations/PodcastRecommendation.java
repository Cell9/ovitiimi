package ohtu.database.entities.recommendations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.*;

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

    @Override
    public RecommendationType getType() {
        return RecommendationType.PODCAST;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.getTitle(), this.getAuthor(), this.getDescription());
    }
}
