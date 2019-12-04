package ohtu.database.entities.recommendations;

import java.util.List;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import ohtu.database.entities.data.Course;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue("youtube")
public class YoutubeRecommendation extends Recommendation {

    @NotEmpty
    private String author;

    @NotEmpty
    private String url;

    @NotEmpty
    private String description;

    public YoutubeRecommendation(String title, Map<Long, Course> courses, List<String> tags, String author, String url, String description) {
        super(title, courses, tags);
        this.author = author;
        this.url = url;
        this.description = description;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.YOUTUBE;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.getTitle(), this.getAuthor(), this.getDescription());
    }

    
}
