package com.diagnomind.web_server.domain.training_image.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

public interface TrainingImageRepository extends CrudRepository<TrainingImage, Integer> {
    
}
