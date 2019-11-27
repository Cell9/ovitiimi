package ohtu.database.entities.recommendations;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import ohtu.database.entities.data.Link;

@Entity
@DiscriminatorValue("video")
public class VideoRecommendation extends Recommendation  {
	
	@OneToOne
    @JoinColumn(name = "generic_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @Getter private Link link;
    
    public VideoRecommendation() {
    	
    }
    
    public VideoRecommendation(Link link) {
    	this.link = link;
    }

	@Override
	public String getTitle() {
		return this.link.getTitle();
	}

	@Override
	public RecommendationType getType() {
		return RecommendationType.VIDEO;
	}
	
    @Override
    public String toString() {
    	return String.format("%s: %s", this.link.getTitle(), this.link.getUrl());
    }
}
