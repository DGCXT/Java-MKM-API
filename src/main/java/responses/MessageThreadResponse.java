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

//	@XStreamAlias("thread")
//	private MessageThread thread;
	
	@XStreamAlias("partner")
	private User partner;
	@XStreamAlias("message")
	private Message message;
	private int unreadMessages;
	
	@XStreamImplicit(itemFieldName="link")
	private List<Link> links;
	
	public int getUnreadMessages() {
		return unreadMessages;
	}
	public void setUnreadMessages(int unreadMessages) {
		this.unreadMessages = unreadMessages;
	}
	public User getPartner() {
		return partner;
	}
	public void setPartner(User partner) {
		this.partner = partner;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
}
