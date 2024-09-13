package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.CommentService;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.comment.CreateCommentRequest;
import com.socialMedia.dtos.comment.DeleteCommentRequest;
import com.socialMedia.dtos.comment.GetAllCommentResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentsController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/getallbyuser")
	public PageResponse<GetAllCommentResponse> getCommentsByUserId() {
		return commentService.getCommentsByUserId();
	}

	@PostMapping("/create")
	public SuccessResponse create(@Valid @RequestBody CreateCommentRequest request) {
		commentService.create(request);
		return new SuccessResponse();
	}

	@PostMapping("/delete")
	public SuccessResponse delete(@RequestBody DeleteCommentRequest request) {
		commentService.delete(request);
		return new SuccessResponse();
	}
}
