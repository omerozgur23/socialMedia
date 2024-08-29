package com.socialMedia.dataAccess;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.UserTweet;

@Repository
public interface UserTweetRepository extends JpaRepository<UserTweet, UUID> {

}
