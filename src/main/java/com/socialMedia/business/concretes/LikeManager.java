package com.socialMedia.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.CommentService;
import com.socialMedia.business.abstracts.LikeService;
import com.socialMedia.business.abstracts.TweetService;
import com.socialMedia.business.rules.like.CommentLikeBusinessRules;
import com.socialMedia.business.rules.like.TweetLikeBusinessRules;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.dataAccess.CommentRepository;
import com.socialMedia.dataAccess.TweetRepository;
import com.socialMedia.dtos.like.CreateCommentLikeRequest;
import com.socialMedia.dtos.like.CreateTweetLikeRequest;
import com.socialMedia.entities.Comment;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;

@Service
public class LikeManager implements LikeService {

	@Autowired
	private TweetLikeBusinessRules tweetLikeBusinessRules;

	@Autowired
	private CommentLikeBusinessRules commentLikeBusinessRules;

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private TweetService tweetService;

	@Autowired
	private CommentService commentService;

	@Override
	public void tweetLike(CreateTweetLikeRequest request) {
		User currentUser = AuthenticatedUser.getCurrentUser();
		Tweet tweet = tweetService.getTweet(request.getTweetId());
		tweetLikeBusinessRules.validateIfUserLikeTweet(currentUser, tweet);
		tweet.getUserLikes().add(currentUser);
		tweetRepository.save(tweet);
	}

	@Override
	public void commentLike(CreateCommentLikeRequest request) {
		User currentUser = AuthenticatedUser.getCurrentUser();
		Comment comment = commentService.getComment(request.getCommentId());
		commentLikeBusinessRules.validateIfUserLikeComment(currentUser, comment);
		comment.getUserCommentLikes().add(currentUser);
		commentRepository.save(comment);
	}
}
