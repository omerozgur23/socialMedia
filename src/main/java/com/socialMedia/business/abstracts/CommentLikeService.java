package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.entities.CommentLike;

public interface CommentLikeService {

	List<CommentLike> getCommentLikesByUserId(UUID id);

}
