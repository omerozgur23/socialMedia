package com.socialMedia.dataAccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.TweetImage;

@Repository
public interface TweetImageRepository extends JpaRepository<TweetImage, UUID> {

	Optional<List<TweetImage>> findByTweetIdIn(List<UUID> tweetId);
}
