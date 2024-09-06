package com.socialMedia.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	private UUID id;

	private String email;

	private String username;

	private String name;

	private String profilePhoto;

	private LocalDate birthDate;

	private String password;

	private String description;

	private boolean isProAccount;

	private LocalDateTime createdDate;

	private LocalDateTime deletedDate;

	public User(String username, String name, String email, String password, LocalDate birthDate) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
	}

	@Column(name = "is_active")
	private boolean enabled = false;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "user")
	private List<UserTweet> userTweet;

	@ManyToMany(mappedBy = "userLikes")
	private List<Tweet> likes;

	@ManyToMany(mappedBy = "userRetweets")
	private List<Tweet> retweets;

	@OneToMany(mappedBy = "user")
	private List<Comment> userComment;

	@ManyToMany(mappedBy = "userComments")
	private List<Comment> comments;

	@OneToMany(mappedBy = "sendingUser")
	private List<Message> sendMessages;

	@OneToMany(mappedBy = "recipientUser")
	private List<Message> recipientMessages;

	@ManyToMany
	@JoinTable(name = "followers", joinColumns = { @JoinColumn(name = "following_user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "follower_user_id") })
	private List<User> followings;

	@ManyToMany(mappedBy = "followings")
	private List<User> followers;

	@OneToMany(mappedBy = "creatorUser")
	private List<Survey> creatorUsers;

	@OneToMany(mappedBy = "evaluatingUser")
	private List<Survey> evaluatingUsers;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

//    @Override
//    public boolean isAccountNonLocked() {
//        return !locked;
//    }

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
