package chungoose.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="Content")
@Table(name="content")
public class Content{
	
	@EmbeddedId
	private ContentId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@MapsId("draftId")
	private Draft draft;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("attributeId")
	private Attribute attribute;
	
	@Column(name="value_1")
	private String value1 = "";
	
	@Column(name="value_2")
	private String value2 = "";
	
	@Column(name="value_3")
	private String value3 = "";
	
	public Content() {}

	public Content(Draft draft, Attribute attribute, String value1, String value2, String value3) {
		super();
		this.draft = draft;
		this.attribute = attribute;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.id = new ContentId(draft.getDraftId(), attribute.getAttributeId());
	}
	
	public Content(Draft draft, Attribute attribute) {
		super();
		this.draft = draft;
		this.attribute = attribute;
		this.value1 = "";
		this.value2 = "";
		this.value3 = "";
		this.id = new ContentId(draft.getDraftId(), attribute.getAttributeId());
	}

	public ContentId getId() {
		return id;
	}
	
	public void setId(int draftId, int attributeId) {
		this.id = new ContentId(draftId, attributeId);
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	
	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}
	
	

	@Override
	public String toString() {
		return "\\n\\t[[" + attribute.toString() + "], value1=" + value1 + ", value2=" + value2 + ", value3=" + value3
				+ "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Content that = (Content) o;
        return Objects.equals(draft, that.draft) &&
               Objects.equals(attribute, that.attribute);
    }
	
	 @Override
	    public int hashCode() {
	    	return Objects.hash(draft, attribute);
	    }
 
	
	
}
