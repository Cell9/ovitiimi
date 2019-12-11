package ohtu.database.entities.recommendations;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private static final Pattern YOUTUBE_VIDEO_CODE = Pattern.compile("http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch(?:\\/|\\?v=)|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?‌​[\\w\\?‌​=]*)?", Pattern.CASE_INSENSITIVE);
	
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
    
    //TODO: Only save video code, don't accept any weird non-youtube link
    public String getVideoCode() {
    	Matcher matcher = YoutubeRecommendation.YOUTUBE_VIDEO_CODE.matcher(this.url);
    	if (matcher.find()) {
    		return matcher.group(1);
    	} else {
    		return null;
    	}
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.getTitle(), this.getAuthor(), this.getDescription());
    }
    
    public void copyTo(YoutubeRecommendation to) {
    	to.author = this.author;
    	to.url = this.url;
    	to.description = this.description;
    }
}
