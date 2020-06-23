package chungoose.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContentId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582519952809943811L;

	@Column(name="draft_id")
	private int draftId;
	
	@Column(name="attribute_id")
	private int attributeId;
	
	private ContentId() {
		// No-args
	}
	
	public ContentId(int draftId, int attributeId) {
		this.draftId = draftId;
		this.attributeId = attributeId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		
		ContentId that = (ContentId) o;
		return Objects.equals(draftId, that.draftId) &&
			   Objects.equals(attributeId, that.attributeId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(draftId, attributeId);
	}
}
