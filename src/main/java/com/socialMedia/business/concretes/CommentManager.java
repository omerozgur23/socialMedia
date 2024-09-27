package com.socialMedia.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.CommentService;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.TweetCommentRepository;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.comment.CreateCommentRequest;
import com.socialMedia.dtos.comment.DeleteCommentRequest;
import com.socialMedia.dtos.comment.GetAllCommentResponse;
import com.socialMedia.entities.TweetComment;
import com.socialMedia.entities.User;

@Service
public class CommentManager implements CommentService {

	@Autowired
	private TweetCommentRepository commentRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Override
	public TweetComment getComment(UUID id) {
		return commentRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.COMMENT_NOT_FOUND + id));
	}

	@Override
	public PageResponse<GetAllCommentResponse> getCommentsByUserId() {
		User currentUser = AuthenticatedUser.getCurrentUser();
		List<TweetComment> comments = commentRepository.findByUserId(currentUser.getId());
		List<GetAllCommentResponse> response = comments.stream()
				.map(comment -> modelMapper.forResponse().map(comment, GetAllCommentResponse.class)).toList();
		int count = response.size();
		return new PageResponse<GetAllCommentResponse>(count, response);
	}

	@Override
	public void create(CreateCommentRequest request) {
		User currentUser = AuthenticatedUser.getCurrentUser();
		TweetComment comment = modelMapper.forRequest().map(request, TweetComment.class);
		comment.setCreatedDate(LocalDateTime.now());
		comment.setUser(currentUser);
		commentRepository.save(comment);
	}

	@Override
	public void delete(DeleteCommentRequest request) {
		TweetComment comment = getComment(request.getId());
		commentRepository.delete(comment);
	}
}
