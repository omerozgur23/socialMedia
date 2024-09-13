package com.socialMedia.business.abstracts;

import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.comment.CreateCommentRequest;
import com.socialMedia.dtos.comment.DeleteCommentRequest;
import com.socialMedia.dtos.comment.GetAllCommentResponse;
import com.socialMedia.entities.Comment;

public interface CommentService {

	Comment getComment(UUID id);

	PageResponse<GetAllCommentResponse> getCommentsByUserId();

	void create(CreateCommentRequest request);

	void delete(DeleteCommentRequest request);
}
