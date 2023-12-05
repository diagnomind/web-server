package com.diagnomind.web_server.domain.train_image.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.train_image.model.TrainImage;

public interface TrainImageRepository extends CrudRepository<TrainImage, Integer> {
    
}
