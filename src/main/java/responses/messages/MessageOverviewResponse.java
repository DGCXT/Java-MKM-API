package responses.messages;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Link;
import entities.MessageThread;

@XStreamAlias("MessageOverviewResponse")
public class MessageOverviewResponse {

	@XStreamImplicit(itemFieldName="thread")
	private List<MessageThreadResponse> thread = new ArrayList<MessageThreadResponse>();
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links;
	
}
