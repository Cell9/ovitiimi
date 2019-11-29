package ohtu.database.entities.recommendations;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ohtu.database.entities.data.Course;

public class StubRecommendation extends Recommendation {

    @NotEmpty
    @Getter
    @Setter
    private String typeId;

    @Min(1)
    @Getter
    @Setter
    private long entityId;

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public RecommendationType getType() {
        if (this.typeId == null) {
            return null;
        }

        return RecommendationType.valueOf(this.typeId);
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
