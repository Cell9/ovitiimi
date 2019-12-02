package ohtu.database.entities.recommendations;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@NoArgsConstructor
@Data
public class StubRecommendation extends Recommendation {

    @NotEmpty
    private String typeId;

    @Min(1)
    private long entityId;

    @Override
    public RecommendationType getType() {
        if (this.typeId == null) {
            return null;
        }

        return RecommendationType.valueOf(this.typeId);
    }
}
