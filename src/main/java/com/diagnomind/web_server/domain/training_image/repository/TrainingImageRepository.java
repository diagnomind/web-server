package com.diagnomind.web_server.domain.training_image.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

/**
 * Repository interface for managing {@link TrainingImage} entities in the database.
 * Extends the Spring Data {@link CrudRepository} interface.
 *
 * @see TrainingImage {@link TrainingImage}, class
 * @see CrudRepository {@link CrudRepository}, class
 */
public interface TrainingImageRepository extends CrudRepository<TrainingImage, Long> {
    
}
