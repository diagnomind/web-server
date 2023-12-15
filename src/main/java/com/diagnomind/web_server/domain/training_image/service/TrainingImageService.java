package com.diagnomind.web_server.domain.training_image.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.diagnomind.web_server.domain.training_image.model.TrainingImage;
import com.diagnomind.web_server.domain.training_image.repository.TrainingImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingImageService {

    private final TrainingImageRepository trainingImageRepository;

    public TrainingImage addTrainImage(TrainingImage trainingImage) {
        return trainingImageRepository.save(trainingImage);
    }

    public List<TrainingImage> getAllTrainingImages() {
        return StreamSupport
                .stream(trainingImageRepository.findAll()
                        .spliterator(), false)
                .toList();
    }

    public boolean deleteTrainingImage(Long id) {
        trainingImageRepository.deleteById(id);
        return !trainingImageRepository.existsById(id);
    }
}
