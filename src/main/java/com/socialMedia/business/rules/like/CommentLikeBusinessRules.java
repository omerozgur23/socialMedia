package com.socialMedia.business.rules.like;

import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.entities.Comment;
import com.socialMedia.entities.User;

@Service
public class CommentLikeBusinessRules {

	public void validateIfUserLikeComment(User user, Comment comment) {
		if (comment.getUserCommentLikes().contains(user))
			throw new BusinessException("Comment is already liked.");
	}
}
