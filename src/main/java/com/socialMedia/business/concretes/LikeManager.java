package com.socialMedia.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.CommentService;
import com.socialMedia.business.abstracts.LikeService;
import com.socialMedia.business.abstracts.TweetService;
import com.socialMedia.business.rules.like.CommentLikeBusinessRules;
import com.socialMedia.business.rules.like.TweetLikeBusinessRules;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.dataAccess.TweetCommentRepository;
import com.socialMedia.dataAccess.TweetRepository;
import com.socialMedia.dtos.like.CreateCommentLikeRequest;
import com.socialMedia.dtos.like.CreateTweetLikeRequest;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.TweetComment;
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
	private TweetCommentRepository commentRepository;

	@Autowired
	private TweetService tweetService;

	@Autowired
	private CommentService commentService;

	@Override
	public void tweetLike(CreateTweetLikeRequest request) {
		User currentUser = AuthenticatedUser.getCurrentUser();
		Tweet tweet = tweetService.getTweet(request.getTweetId());

		if (tweetLikeBusinessRules.validateIfUserLikeTweet(currentUser, tweet)) {
			tweetUnlike(currentUser, tweet);
			return;
		}

		tweet.getUserLikes().add(currentUser);
		tweetRepository.save(tweet);
	}

	@Override
	public void tweetUnlike(User user, Tweet tweet) {
		tweet.getUserLikes().remove(user);
		tweetRepository.save(tweet);
	}

	@Override
	public void commentLike(CreateCommentLikeRequest request) {
		User currentUser = AuthenticatedUser.getCurrentUser();
		TweetComment comment = commentService.getComment(request.getCommentId());

		if (commentLikeBusinessRules.validateIfUserLikeComment(currentUser, comment)) {
			commentUnlike(currentUser, comment);
			return;
		}

		comment.getUserCommentLikes().add(currentUser);
		commentRepository.save(comment);
	}

	@Override
	public void commentUnlike(User user, TweetComment comment) {
		comment.getCommentLikes().remove(user);
		commentRepository.save(comment);
	}
}
