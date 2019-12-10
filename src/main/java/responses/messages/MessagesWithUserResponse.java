package responses.messages;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Link;
import entities.Message;
import entities.User;

@XStreamAlias("MessagesWithUserResponse")
public class MessagesWithUserResponse {

	private User partner;
	
	@XStreamImplicit(itemFieldName="message")
	private List<Message> message = new ArrayList<>();
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links;
}
