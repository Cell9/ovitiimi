package ohtu.database.entities.recommendations;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import ohtu.database.entities.data.Podcast;

@Entity
@DiscriminatorValue("podcast")
public class PodcastRecommendation extends Recommendation  {
	@OneToOne
    @JoinColumn(name = "generic_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @Getter private Podcast podcast;
    
    public PodcastRecommendation() {
    	
    }
    
    public PodcastRecommendation(Podcast podcast) {
    	this.podcast = podcast;
    }

	@Override
	public String getTitle() {
		return this.podcast.getTitle();
	}
}
