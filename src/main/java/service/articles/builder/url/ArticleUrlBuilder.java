package service.articles.builder.url;

public class ArticleUrlBuilder {
	
	public static String buildArticlesFromUserUrl(String user)
	{
		return "/users/%s/articles" + user;
	}
	
}
