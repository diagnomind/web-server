package com.diagnomind.web_server.domain.training_data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diagnomind.web_server.domain.training_data.model.TrainingData;
import com.diagnomind.web_server.domain.training_data.repository.TrainingDataRepository;
import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingDataService {

    private final TrainingDataRepository trainingDataRepository;
    private static final Long ID_LONG = 1L;

    public Boolean addTrainImage(TrainingImage trainImage) {
        return trainingDataRepository
                .findById(ID_LONG)
                .map(dataTrain -> dataTrain
                        .getTrainingImages()
                        .add(trainImage))
                .orElse(false);
    }

    public List<TrainingImage> getTrainingImages() {
        return trainingDataRepository 
                .findById(ID_LONG)
                .map(TrainingData::getTrainingImages)
                .orElse(List.of());
    }

    public Boolean deleteTrainingData(Long id) {
        return trainingDataRepository
                .findById(ID_LONG)
                .map(trainingData -> trainingData
                        .getTrainingImages()
                        .removeIf(image -> image
                                .getId().equals(id)
                        ))
                .orElse(false);
    }

}
