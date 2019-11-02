package responses;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamInclude;

import entities.Link;
import entities.Message;
import entities.MessageThread;
import entities.User;

public class MessageThreadResponse {

	private User partner;
	private Message message;
	private int unreadMessages;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links;
	
	public int getUnreadMessages() {
		return unreadMessages;
	}
	public void setUnreadMessages(int unreadMessages) {
		this.unreadMessages = unreadMessages;
	}
}
