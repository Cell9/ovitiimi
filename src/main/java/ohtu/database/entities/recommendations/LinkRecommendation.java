package ohtu.database.entities.recommendations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.*;
import ohtu.database.entities.data.Course;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue("video")
public class LinkRecommendation extends Recommendation {

    @NotEmpty
    private String url;

    public LinkRecommendation(String title, List<Course> courses, List<String> tags, String url) {
        super(title, courses, tags);
        this.url = url;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.LINKKI;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.getTitle(), this.getUrl());
    }
}
