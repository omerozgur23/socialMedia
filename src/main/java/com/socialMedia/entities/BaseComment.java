package com.socialMedia.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseComment {

	@Id
	@GeneratedValue
	private UUID id;

	@OneToMany(mappedBy = "commentId")
	private List<CommentLike> commentLikes;

}
