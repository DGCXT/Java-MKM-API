package connector;

public class MkmConnectionResponse {

	private int code;
	private String body;
	
	public MkmConnectionResponse(int responseCode, String responseBody)
	{
		this.code = responseCode;
		this.body = responseBody;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
