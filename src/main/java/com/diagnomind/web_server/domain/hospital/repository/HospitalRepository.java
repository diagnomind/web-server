package com.diagnomind.web_server.domain.hospital.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.hospital.model.Hospital;

/**
 * Repository interface for managing {@link Hospital} entities in the database.
 * Extends the Spring Data {@link CrudRepository} interface.
 *
 * @see Hospital {@link Hospital}, class
 * @see CrudRepository {@link CrudRepository}, class
 */
public interface HospitalRepository extends CrudRepository<Hospital, Long> {
    
}
