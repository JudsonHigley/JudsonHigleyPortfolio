package chungoose.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "site_user")

public class User {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "user_username", nullable = false, unique = true, length = 20)
	@Length(min = 3)
	private String username;

	@Column(name = "user_password", nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "user_email", nullable = false)
	private String email;
	
	@Column(name="user_created")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCreated;

	@OneToMany(mappedBy="author", fetch=FetchType.EAGER)
	@JsonBackReference
	private List<Draft> draftList = new ArrayList<>();

	public User(int userId, @Length(min = 3) String username,  String email, String password, Date userCreated, List<Draft> draftList) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userCreated = userCreated;
		this.draftList = draftList;
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public User() {
		// No-args
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public Date getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Date userCreated) {
		this.userCreated = userCreated;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if(o==null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(userId, user.userId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	public List<Draft> getDraftList() {
		return draftList;
	}
	
	public void setDraftList(List<Draft> draftList) {
		this.draftList = draftList;
	}

	@Override
	public String toString() {
		return "\n User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", userCreated=" + userCreated + ", \n\tdraftList=" + draftList + "\n\t]";
	}
}
