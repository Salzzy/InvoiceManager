package de.format.salzzy.Rechnungsmanager.model.auth;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

import de.format.salzzy.Rechnungsmanager.model.Activity;
import de.format.salzzy.Rechnungsmanager.model.Document;
import de.format.salzzy.Rechnungsmanager.model.UserInfo;
import de.format.salzzy.Rechnungsmanager.model.auth.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String username;
	
	private String password;
	
	@Transient
    private String passwordConfirm;

	private boolean isAccountNonExpired;

	private boolean isAccountNonLocked;

	private boolean isCredentialsNonExpired;

	private boolean isEnabled;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_info_id", referencedColumnName="id", insertable=true, updatable=true)
	private UserInfo userInfo;

	@OneToMany(mappedBy = "owner")
	private Set<Document> document;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	@OneToMany(mappedBy = "receiver")
	private Set<Activity> activitySet;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRole().getGrantedAuthority();
	}

	public User(String username, String password, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, Role role) {
		this.username = username;
		this.password = password;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.role = role;
	}
}
