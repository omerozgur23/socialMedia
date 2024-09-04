package com.socialMedia.dataAccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.UserTweet;

@Repository
public interface UserTweetRepository extends JpaRepository<UserTweet, UUID> {

	Optional<UserTweet> findByUserIdAndTweetId(UUID userId, UUID tweetId);

	Optional<UserTweet> findByTweetId(UUID tweetId);

	Optional<List<UserTweet>> findByUserId(UUID userId);
}
