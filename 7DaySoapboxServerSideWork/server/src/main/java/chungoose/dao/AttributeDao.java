package chungoose.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import chungoose.model.Attribute;

@Transactional
@Repository("attributeDao")
public class AttributeDao {
	
	
	private SessionFactory sesFact;
	
	public AttributeDao() {
		// No-args
	}
	
	@Autowired
	public AttributeDao(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	public void insert(Attribute attribute) {
		sesFact.getCurrentSession().save(attribute);
	}
	
	public List<Attribute> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Attribute", Attribute.class).list();
	}
	
	public Attribute selectById(int id) {
		return sesFact.getCurrentSession().get(Attribute.class, id);
	}

}
