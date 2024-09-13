package com.socialMedia.dataAccess;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

	List<Comment> findByUserId(UUID userId);
}
