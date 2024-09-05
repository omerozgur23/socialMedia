package com.socialMedia.dataAccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.TweetVideo;

@Repository
public interface TweetVideoRepository extends JpaRepository<TweetVideo, UUID> {

	Optional<List<TweetVideo>> findByTweetIdIn(List<UUID> tweetId);
}
