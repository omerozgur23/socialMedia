package com.socialMedia.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.socialMedia.entities.enums.Status;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

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

	private boolean isProAccount = false;

	private boolean isPrivate = false;

	private LocalDateTime createdDate;

	private LocalDateTime deletedDate;

	@Enumerated(EnumType.STRING)
	private Status status = Status.UNCONFIRMED;

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

	@OneToMany(mappedBy = "user")
	private List<SurveyAnswer> surveyAnswers;

	@ManyToMany
	@JoinTable(name = "followers", joinColumns = { @JoinColumn(name = "follower_user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "following_user_id") })
	private List<User> followings;

	@ManyToMany(mappedBy = "followings")
	private List<User> followers;

	@OneToMany(mappedBy = "creatorUser")
	private List<Survey> creatorUsers;

//	@OneToMany(mappedBy = "evaluatingUser")
//	private List<Survey> evaluatingUsers;

	@ManyToMany
	@JoinTable(name = "blocked_ones", joinColumns = { @JoinColumn(name = "obstructive_id") }, inverseJoinColumns = {
			@JoinColumn(name = "blocked_id") })
	private List<User> blockedUsers;

}
