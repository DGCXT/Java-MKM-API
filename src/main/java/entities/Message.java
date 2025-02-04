package entities;

import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Message {

	private String idMessage;
	private boolean isSending;
	private String date;
	private String text;
	private boolean unread;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links;
	
	@Override
	public String toString() {
		return getText();
	}
	
	
	public String getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}
	public boolean isSending() {
		return isSending;
	}
	public void setSending(boolean isSending) {
		this.isSending = isSending;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isUnread() {
		return unread;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	
	
	
}
