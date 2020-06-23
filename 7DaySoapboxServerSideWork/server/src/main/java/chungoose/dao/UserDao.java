package chungoose.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import chungoose.model.User;

@Transactional
@Repository("userDao")
public class UserDao {
	
	private SessionFactory sesFact;
	
	public UserDao() {
		// No-args
	}
	
	@Autowired
	public UserDao(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	public void insert(User user) {
		sesFact.getCurrentSession().save(user);
	}
	
	public void update(User user) {
		sesFact.getCurrentSession().saveOrUpdate(user);
	}
	
	public User selectById(int id) {
		return sesFact.getCurrentSession().get(User.class, id);
	}
	
	public User selectByLogin(String username, String password) {
		User user = new User();
		String template = "from User u where u.username = '%s' and u.password = '%s'";
		String query = String.format(template, username, password);
		try{
			user = sesFact.getCurrentSession().createQuery(query, User.class).getSingleResult();
		} catch(NoResultException e) {
			e.printStackTrace();
		}
			
		return user;
	}

	
	public List<User> selectAll() {
		return sesFact.getCurrentSession().createQuery("from User", User.class).list();
	}
}
