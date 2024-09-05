package com.socialMedia.dataAccess;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.VerificationToken;

@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, UUID> {

	VerificationToken findByToken(String token);
}
