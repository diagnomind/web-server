package com.diagnomind.web_server.domain.training_data.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.training_data.model.TrainingData;

public interface TrainingDataRepository extends CrudRepository<TrainingData, Integer> {
    
}
