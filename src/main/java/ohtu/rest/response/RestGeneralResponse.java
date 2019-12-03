package ohtu.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestGeneralResponse {
	
	private boolean success;
	private String error;
	
	private RestGeneralResponse() {
		this.success = true;
	}
	
	private RestGeneralResponse(String error) {
		this.error = error;
	}
	
	public static RestGeneralResponse success() {
		return new RestGeneralResponse();
	}
	
	public static RestGeneralResponse error(String reason) {
		return new RestGeneralResponse(reason);
	}
}
