package vn.ptit.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tbl_user")
public class UserInfo extends BaseEntity implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username", length = 100, nullable = false)
	private String username;
	
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "fullname", length = 100, nullable = false)
	private String fullname;
	
	@Column(name = "phonenumber", length = 100, nullable = false)
	private String phonenumber;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "address", length = 100, nullable = false)
	private String address;
	
	@Column(name = "status", nullable = true)
	private Boolean status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userInfo", fetch = FetchType.EAGER)
	private UserAttachment userAttachment = new UserAttachment();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo", fetch = FetchType.LAZY)
	private List<Bill> bills = new ArrayList<>();
	
	public void addUserAttachment(UserAttachment userAttachment) {
		this.userAttachment = userAttachment;
		userAttachment.setUserInfo(this);
	}
	
	public void removeUserAttachment(UserAttachment userAttachment) {
		this.userAttachment = null;
		userAttachment.setUserInfo(null);
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public UserAttachment getUserAttachment() {
		return userAttachment;
	}

	public void setUserAttachment(UserAttachment userAttachment) {
		this.userAttachment = userAttachment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
