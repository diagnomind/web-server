package com.diagnomind.web_server.domain.training_data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diagnomind.web_server.domain.training_data.model.TrainingData;
import com.diagnomind.web_server.domain.training_data.repository.TrainingDataRepository;
import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingDataService {

    private final TrainingDataRepository trainingDataRepository;

    public Boolean addTrainImage(TrainingImage trainImage) {
        return trainingDataRepository
                .findById(1L)
                .map(dataTrain -> dataTrain
                        .getTrainingImages()
                        .add(trainImage))
                .orElse(false);
    }
    
//     no implemented
    List<TrainingImage> getTrainingImages() {
        return trainingDataRepository 
                .findById(1L)
                .map(TrainingData::getTrainingImages)
                .orElse(List.of());
    }
  
//    no implemented
    // Boolean deleteTrainingData(Integer id) {
    //     return trainingDataRepository
    //             .findById(1)
    //             .map(traininData -> traininData
    //                     .getTrainingImages()
    //                     .remove())
    //             .orElse(false);
    // }

}
