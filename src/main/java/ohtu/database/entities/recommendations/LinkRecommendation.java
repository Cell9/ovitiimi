package ohtu.database.entities.recommendations;

import java.util.ArrayList;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

import lombok.Getter;
import ohtu.database.entities.data.Course;
import ohtu.database.entities.data.Link;

@Entity
@DiscriminatorValue("video")
@Data
public class LinkRecommendation extends Recommendation {

    @Id
    private Long id;
    private String url;

    public LinkRecommendation() {
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.VIDEO;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.getTitle(), this.getUrl());
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
