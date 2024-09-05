package com.socialMedia.dataAccess;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, UUID> {

//	Optional<List<Tweet>> findById(List<UUID> tweetsId);
}
