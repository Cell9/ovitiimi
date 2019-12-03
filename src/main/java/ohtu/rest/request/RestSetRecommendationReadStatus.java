package ohtu.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestSetRecommendationReadStatus {
	private long id;
	private boolean read;
}
