package com.socialMedia.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class TweetComment extends BaseComment {

	private String comment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "tweet_id")
	private Tweet tweet;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "deleted_date")
	private LocalDateTime deletedDate;

	@Column(name = "is_deleted")
	private boolean isDeleted = false;

	@OneToMany(mappedBy = "tweetComment")
	private List<CommentReply> commentReplies;

//	@ManyToMany
//	@JoinTable(name = "comment_likes", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private List<User> userCommentLikes;
}
