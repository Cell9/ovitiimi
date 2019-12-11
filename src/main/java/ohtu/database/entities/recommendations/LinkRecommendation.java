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
@DiscriminatorValue("video")
public class LinkRecommendation extends Recommendation {

    @NotEmpty
    private String url;

    public LinkRecommendation(String title, Map<Long, Course> courses, List<String> tags, String url) {
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
    
	public void copyTo(LinkRecommendation to) {
		super.copyTo(to);
		
		to.url = this.url;
	}
}
