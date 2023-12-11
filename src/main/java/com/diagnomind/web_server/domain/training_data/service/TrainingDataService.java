package com.diagnomind.web_server.domain.training_data.service;

import java.util.List;

import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

public interface TrainingDataService {
    void addTrainImage(TrainingImage trainImage);
    List<TrainingImage> getTrainingImages();
    void deleteTrainingData(Integer id);
}
