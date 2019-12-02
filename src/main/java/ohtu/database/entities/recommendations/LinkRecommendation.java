package ohtu.database.entities.recommendations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue("video")
public class LinkRecommendation extends Recommendation {

    @NotEmpty
    private String url;

    @Override
    public RecommendationType getType() {
        return RecommendationType.LINKKI;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.getTitle(), this.getUrl());
    }
}
