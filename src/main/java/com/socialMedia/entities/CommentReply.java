package com.socialMedia.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "comment_replies")
public class CommentReply extends BaseComment {

	private String commentReply;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "tweet_comment_id")
	private TweetComment tweetComment;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "deleted_date")
	private LocalDateTime deletedDate;

	@Column(name = "is_deleted")
	private boolean isDeleted = false;
}
