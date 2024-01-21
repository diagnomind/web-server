package com.diagnomind.web_server.domain.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.user.model.User;

/**
 * Repository interface for managing {@link User} entities in the database.
 * Extends the Spring Data {@link CrudRepository} interface.
 *
 * @see User {@link User}, class
 * @see CrudRepository {@link CrudRepository}, class
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
}
