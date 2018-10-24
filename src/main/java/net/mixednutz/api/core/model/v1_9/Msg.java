package net.mixednutz.api.core.model.v1_9;

import java.time.ZonedDateTime;
import java.util.List;

public class Msg extends ApiObject<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3441950957997424149L;
	
	private String body;
	private ZonedDateTime dateCreated; //creation Date
	private String subject;
	
	private UserSmall author;
	private Msgthread thread;
	
	private String uri;
	private String url;
	
	private List<String> reactions;
	
	public Msg() {
		super();
	}
	public Msg(Integer id) {
		super(id);
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public UserSmall getAuthor() {
		return author;
	}
	public void setAuthor(UserSmall author) {
		this.author = author;
	}
	public Msgthread getThread() {
		return thread;
	}
	public void setThread(Msgthread thread) {
		this.thread = thread;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getReactions() {
		return reactions;
	}
	public void setReactions(List<String> reactions) {
		this.reactions = reactions;
	}
	
}
