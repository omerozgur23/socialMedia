package com.socialMedia.dataAccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.CommentLike;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, UUID> {

	Optional<List<CommentLike>> findByUserId(UUID id);

}
