package com.socialMedia.dataAccess;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.auth.ConfirmationToken;

import jakarta.transaction.Transactional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, UUID> {

	Optional<ConfirmationToken> findByToken(String token);

	@Transactional
	@Modifying
	@Query("UPDATE ConfirmationToken c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
	int updateConfirmedAt(String token, LocalDateTime confirmedAt);

//	@Query("SELECT c.id FROM ConfirmationToken c " + "WHERE c.expires_at > c.created_at + INTERVAL '15 minutes'")
	@Query(value = "SELECT * FROM confirmation_tokens WHERE expires_at > DATE_ADD(created_at, INTERVAL 1 MINUTE)", nativeQuery = true)
	List<ConfirmationToken> findByConfirmedAt();
}
