package entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MessageThread{
	
	@XStreamAlias("partner")
	private User partner;
	@XStreamAlias("message")
	private Message message;
	private int unreadMessages;
	
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
