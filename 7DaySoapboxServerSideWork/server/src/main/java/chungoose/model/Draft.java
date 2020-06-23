package chungoose.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name="Draft")
@Table(name="draft")
public class Draft {
	
	@Id
	@Column(name="draft_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int draftId;
	
	@Column(name="draft_title", nullable=false)
	private String draftTitle;
	
	@Column(name="draft_column_pic")
	private String draftColumnPic;
	
	@Column(name="draft_created")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date draftCreated;
	
	@Column(name="draft_updated")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date draftUpdated;
	
	/*
	 * When a draft is submitted the draft_updated field acts as the day it was submitted.
	 * While a draft is submitted a user cannot edit their draft.
	 * However, A user should be able to revoke their submission and continue editing their draft.
	 * 
	 * When a draft is selected the draft_updated field acts as the day it was selected.
	 * After a draft is selected a user can never edit their draft again.  
	 * 
	 */
	
	@Column(name="draft_submitted")
	private boolean draftSubmitted;
	
	@Column(name="draft_selected")
	private boolean draftSelected; 
	
	@ManyToOne(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="author_user_fk")
	@JsonManagedReference
	private User author;
	
	@OneToMany(
			mappedBy = "draft", 
			cascade=CascadeType.ALL,
			orphanRemoval = true,
			fetch=FetchType.EAGER)
	private List<Content> contentList = new ArrayList<>();
	
	public Draft() {}

	public Draft(int draftId, String draftTitle,String draftColumnPic, Date draftCreated, Date draftUpdated, boolean draftSubmitted, boolean draftSelected,
			User author, List<Content> contentList) {
		super();
		this.draftId = draftId;
		this.draftTitle = draftTitle;
		this.draftColumnPic = draftColumnPic;
		this.draftCreated = draftCreated;
		this.draftUpdated = draftUpdated;
		this.draftSubmitted = draftSubmitted;
		this.draftSelected = draftSelected;
		this.author = author;
		this.contentList = contentList;
	}

	public Draft(String draftTitle, User author) {
		super();
		this.draftTitle = draftTitle;
		this.author = author;
	}
	
	//add attributes
	
	public void addAttribute(Attribute attribute) {
		Content content = new Content(this, attribute);
		contentList.add(content);
	}
	
	public void addAttributeWContent(Attribute attribute, String value1, String value2, String value3) {
		Content content = new Content(this, attribute, value1, value2, value3);
		contentList.add(content);
	}
	
	public void addAttributes(List<Attribute> attributes) {
		for(Attribute attribute : attributes) {
			addAttribute(attribute);
		}
	}
	
	public void removeAttribute(Attribute attribute) {
		for(Iterator<Content> iterator = contentList.iterator(); iterator.hasNext();) {
			Content content = iterator.next();
			
			if(content.getDraft().equals(this) && content.getAttribute().equals(attribute)) {
				iterator.remove();
				content.setDraft(null);
				content.setAttribute(null);
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this ==o) return true;
		if(o ==null || getClass() != o.getClass()) return false;
		Draft draft = (Draft) o;
		return Objects.equals(draftId, draft.draftId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(draftId, draftTitle);
	}
	
	//getters and setters

	public int getDraftId() {
		return draftId;
	}

	public void setDraftId(int draftId) {
		this.draftId = draftId;
	}

	public String getDraftTitle() {
		return draftTitle;
	}

	public void setDraftTitle(String draftTitle) {
		this.draftTitle = draftTitle;
	}

	public String getDraftColumnPic() {
		return draftColumnPic;
	}

	public void setDraftColumnPic(String draftColumnPic) {
		this.draftColumnPic = draftColumnPic;
	}

	public Date getDraftCreated() {
		return draftCreated;
	}

	public void setDraftCreated(Date draftCreated) {
		this.draftCreated = draftCreated;
	}

	public Date getDraftUpdated() {
		return draftUpdated;
	}

	public void setDraftUpdated(Date draftUpdated) {
		this.draftUpdated = draftUpdated;
	}

	public boolean isDraftSubmitted() {
		return draftSubmitted;
	}

	public void setDraftSubmitted(boolean draftSubmitted) {
		this.draftSubmitted = draftSubmitted;
	}

	public boolean isDraftSelected() {
		return draftSelected;
	}

	public void setDraftSelected(boolean draftSelected) {
		this.draftSelected = draftSelected;
	}

	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Content> getContent() {
		return contentList;
	}

	public void setContent(List<Content> contentList) {
		this.contentList = contentList;
	}

	@Override
	public String toString() {
		return "\n\t\tDraft [draftId=" + draftId + ", draftTitle=" + draftTitle + ", \n\t\tdraftCreated=" + draftCreated
				+ ", draftUpdated=" + draftUpdated + ", draftSubmitted=" + draftSubmitted + ", \n\t\tauthor=" + author.getUsername() + 
				", content=" + contentList + "\n\t\t]";
	}
}
