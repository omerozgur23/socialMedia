package com.socialMedia.entities.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import com.socialMedia.entities.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmation_tokens")
@Builder
public class ConfirmationToken {

	@Id
	@GeneratedValue()
	private UUID id;

	private String token;

	private LocalDateTime createdAt;

	private LocalDateTime expiresAt;

	private LocalDateTime confirmedAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
