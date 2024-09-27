package com.socialMedia.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.CommentLikeService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.CommentLikeRepository;
import com.socialMedia.entities.CommentLike;

@Service
public class CommentLikeManager implements CommentLikeService {

	@Autowired
	private CommentLikeRepository commentLikeRepository;

	@Override
	public List<CommentLike> getCommentLikesByUserId(UUID id) {
		return commentLikeRepository.findByUserId(id)
				.orElseThrow(() -> new BusinessException(Messages.USER_IS_COMMENT_LIKES_NOT_FOUND));
	}

}
