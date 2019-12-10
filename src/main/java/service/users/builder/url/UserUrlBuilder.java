package service.users.builder.url;

public class UserUrlBuilder {

	public static String buildDiscoverUsersUrl(String username)
	{
		return "/users/find?search=" + username.toLowerCase();
	}
	
	public static String buildGetUserUrl(String username)
	{
		return "/users/" + username;
	}
}
