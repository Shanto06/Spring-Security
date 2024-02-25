package com.polygon.technology.springSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polygon.technology.springSecurity.entity.*;

/**
 * Repository interface for interacting with the database for UserEntity entities.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return The UserEntity associated with the provided email address.
     */
    UserEntity findByEmail(String email);
}
