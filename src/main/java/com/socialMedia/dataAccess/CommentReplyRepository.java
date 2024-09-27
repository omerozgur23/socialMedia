package com.socialMedia.dataAccess;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.CommentReply;

@Repository
public interface CommentReplyRepository extends JpaRepository<CommentReply, UUID> {

}
