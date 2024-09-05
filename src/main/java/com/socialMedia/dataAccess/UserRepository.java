package com.socialMedia.dataAccess;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	@Query("SELECT u.id FROM User u WHERE u.status != Status.ACTIVE AND u.deletedDate <= :timeStamp")
	List<UUID> findByStatus(@Param("timeStamp") LocalDateTime timeStamp);

	@Modifying
	@Query("DELETE FROM User u WHERE u.status != Status.ACTIVE AND u.deletedDate <= :timeStamp")
	void hardDelete(@Param("timeStamp") LocalDateTime timeStamp);
}
