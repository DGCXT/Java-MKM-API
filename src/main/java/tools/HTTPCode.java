package tools;

public enum HTTPCode {

	BAD_REQUEST(307, "Temporary Redirect.", "Particular requests can deliver thousands of entities (e.g. a large stock or requesting articles for a specified product, and many more). Generally all these request allow you to paginate the results - either returning a 206 or 204 HTTP status code. Nevertheless, all these requests can also be done without specifying a pagination. If done and the resulting entities would be more than 1,000 the request will respond with a 307, specifying the paginated request. However, you should switch of the behaviour to automatically redirect to the given request URI, because a new Authorization header needs to be compiled for the redirected resource.")
	;
	
	private int value;
	private String name;
	private String description;
	
	private HTTPCode(int value, String name, String description)
	{
		
	}
}
