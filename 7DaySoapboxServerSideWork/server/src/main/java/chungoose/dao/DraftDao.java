package chungoose.dao;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import chungoose.model.Draft;

@Transactional
@Repository("draftDao")
public class DraftDao {
	
	private SessionFactory sesFact;
	
	public DraftDao() {
		// No-args
	}
	
	@Autowired
	public DraftDao(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	public void insert(Draft draft) {
		sesFact.getCurrentSession().save(draft);
	}
	
	public Draft selectById(int id) {
		return sesFact.getCurrentSession().get(Draft.class, id);
	}
	
	public List<Draft> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Draft", Draft.class).list();
	}
	
	public List<Draft> selectAllSubmitted() {
		return sesFact.getCurrentSession().createQuery("from Draft d where d.draftSelected=false and d.draftSubmitted=true", Draft.class).list();
	}
	
	public List<Draft> selectAllSelected(){
		return sesFact.getCurrentSession().createQuery("from Draft d where d.draftSelected=true order by d.draftUpdated desc", Draft.class).list();
	}
	
	public void saveOrUpdate(Draft draft) {
		sesFact.getCurrentSession().saveOrUpdate(draft);
	}

	public Draft getMostRecentByAuthor(int authorId) {
		String template = "From Draft d where d.author.userId = %d order by d.draftUpdated desc";
		String query = String.format(template, authorId);
		return sesFact.getCurrentSession().createQuery(query, Draft.class).setMaxResults(1).getSingleResult();
	}
}
