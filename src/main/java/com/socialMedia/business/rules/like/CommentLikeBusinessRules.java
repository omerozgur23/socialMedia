package com.socialMedia.business.rules.like;

import org.springframework.stereotype.Service;

import com.socialMedia.entities.Comment;
import com.socialMedia.entities.User;

@Service
public class CommentLikeBusinessRules {

	public boolean validateIfUserLikeComment(User user, Comment comment) {
		if (comment.getUserCommentLikes().contains(user))
			return true;
		return false;
	}
}
