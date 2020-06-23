package chungoose.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import chungoose.model.Content;

@Transactional
@Repository("contentDao")
public class ContentDao {
	
	private SessionFactory sesFact;
	
	public ContentDao() {
		// No-args
	}
	
	@Autowired
	public ContentDao(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}
	
	public void saveOrUpdate(Content content) {
		sesFact.getCurrentSession().saveOrUpdate(content);
	}


	public void insert(Content content) {
		sesFact.getCurrentSession().save(content);
	}
	
	public List<Content> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Content", Content.class).list();
	}

}