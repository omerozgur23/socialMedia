package com.socialMedia.dataAccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.TweetQuote;

@Repository
public interface TweetQuoteRepository extends JpaRepository<TweetQuote, UUID> {

	Optional<List<TweetQuote>> findByUserIdIn(List<UUID> usersId);
}
