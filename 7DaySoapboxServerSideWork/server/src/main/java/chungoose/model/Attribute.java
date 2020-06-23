package chungoose.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Attribute")
@Table(name="attribute")
public class Attribute {
	
	@Id
	@GeneratedValue
	private int attributeId;
	
	@Column(name="attribute_type")
	private String type;
	
	@Column(name="attribute_size")
	private String size;
	
	@Column(name="col")
	private String col;
	
	@Column(name="placement")
	private String placement;
	
	
	public Attribute() {
		// No-args
	}
	
	public Attribute(String type, String size, String col, String placement) {
		super();
		this.type = type;
		this.size = size;
		this.col = col;
		this.placement = placement;
	}
	
	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}
	
	@Override
	public String toString() {
		return type + "-" + size + "-" + col + "-" + placement;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Attribute attribute = (Attribute) o;
		return Objects.equals(attributeId, attribute.getAttributeId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(attributeId);
	}
}
