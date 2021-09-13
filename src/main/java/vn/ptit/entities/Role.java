package vn.ptit.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tbl_role")
public class Role extends BaseEntity implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "description", length = 100, nullable = false)
	private String description;
	
	@Column(name = "status", nullable = true)
	private Boolean status;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private List<UserInfo> users = new ArrayList<UserInfo>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
	
	@Override
	public String getAuthority() {

		return name;
	}
	
	

}
