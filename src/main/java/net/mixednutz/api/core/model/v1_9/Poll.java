package net.mixednutz.api.core.model.v1_9;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.mixednutz.api.core.model.TimelineElement;

@JsonInclude(Include.NON_NULL)
public class Poll extends InternalTimelineElement<Integer> {
	
	private UserSmall author; //User who started
	private boolean public_;
		
	private String question;
	
	private List<Option> options;
	
	private long sizeOfResponses;
	
	private Integer currentUserSelectionId;
	
	public Poll() {
		super("Poll");
	}
	public UserSmall getAuthor() {
		return author;
	}
	public void setAuthor(UserSmall sharedBy) {
		this.author = sharedBy;
	}
	public boolean isPublic() {
		return public_;
	}
	public void setPublic(boolean public_) {
		this.public_ = public_;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public long getSizeOfResponses() {
		return sizeOfResponses;
	}
	public void setSizeOfResponses(long sizeOfResponses) {
		this.sizeOfResponses = sizeOfResponses;
	}
	
	public Integer getCurrentUserSelectionId() {
		return currentUserSelectionId;
	}
	public void setCurrentUserSelectionId(Integer currentUserSelectionId) {
		this.currentUserSelectionId = currentUserSelectionId;
	}
	
	@Override
	public TimelineElement toTimelineElement() {
		TimelineElement api = super.toTimelineElement();
		api.setPostedByUser(this.author.toUserSmall());
		api.setTitle(this.question);
		return api;
	}

	public static class Option {
		private int id;
		private String text;
		private int numOfVotes;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public int getNumOfVotes() {
			return numOfVotes;
		}
		public void setNumOfVotes(int numOfVotes) {
			this.numOfVotes = numOfVotes;
		}
	}
		
}
