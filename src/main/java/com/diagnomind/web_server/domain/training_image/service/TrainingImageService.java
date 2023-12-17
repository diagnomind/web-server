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

    /**
     * Adds a new training image to the repository.
     *
     * @param trainingImage The TrainingImage object to be added.
     * @return The TrainingImage object that has been saved in the repository.
     */
    public TrainingImage addTrainImage(TrainingImage trainingImage) {
        return trainingImageRepository.save(trainingImage);
    }

    /**
     * Retrieves a list of all training images from the repository.
     *
     * @return A List containing all TrainingImage objects stored in the repository.
     */
    public List<TrainingImage> getAllTrainingImages() {
        return StreamSupport
                .stream(trainingImageRepository.findAll()
                        .spliterator(), false)
                .toList();
    }

    /**
     * Deletes a training image from the repository based on its ID.
     *
     * @param id The ID of the TrainingImage to be deleted.
     * @return {@code true} if the TrainingImage is successfully deleted, {@code false} otherwise.
     */
    public boolean deleteTrainingImage(Long id) {
        trainingImageRepository.deleteById(id);
        return !trainingImageRepository.existsById(id);
    }
}
