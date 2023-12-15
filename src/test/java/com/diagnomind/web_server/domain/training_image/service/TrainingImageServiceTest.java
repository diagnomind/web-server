package com.diagnomind.web_server.domain.training_image.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diagnomind.web_server.domain.training_image.model.TrainingImage;
import com.diagnomind.web_server.domain.training_image.repository.TrainingImageRepository;

class TrainingImageServiceTest extends EasyMockSupport {
 
    private static final Long ID = 1L;

    TrainingImage trainingImage;
    TrainingImageRepository mockTrainingImageRepository;
    TrainingImageService trainingImageService;

    @BeforeEach
    void setup() {
        trainingImage = new TrainingImage();
        mockTrainingImageRepository = createStrictMock(TrainingImageRepository.class);
        trainingImageService = new TrainingImageService(mockTrainingImageRepository);
    }

    @Test
    void addTrainImageCorrectTest() {
        EasyMock.expect(mockTrainingImageRepository.save(trainingImage)).andReturn(trainingImage);
        EasyMock.replay(mockTrainingImageRepository);
        assertEquals(trainingImage, trainingImageService.addTrainImage(trainingImage));
        EasyMock.verify(mockTrainingImageRepository);
    }

    @Test
    void getAllTrainingImagesTest() {
        List<TrainingImage> listTrainingImages = new ArrayList<>();
        EasyMock.expect(mockTrainingImageRepository.findAll()).andReturn(listTrainingImages);
        EasyMock.replay(mockTrainingImageRepository);
        assertEquals(listTrainingImages, trainingImageService.getAllTrainingImages());
        EasyMock.verify(mockTrainingImageRepository);
    }

    @Test
    void deleteTrainingImageCorrectTest() {
        mockTrainingImageRepository.deleteById(ID);
        EasyMock.expect(mockTrainingImageRepository.existsById(ID)).andReturn(false);
        EasyMock.replay(mockTrainingImageRepository);
        assertTrue(trainingImageService.deleteTrainingImage(ID));
        EasyMock.verify(mockTrainingImageRepository);
    }

    @Test
    void deleteTrainingImageFailTest() {
        mockTrainingImageRepository.deleteById(ID);
        EasyMock.expect(mockTrainingImageRepository.existsById(ID)).andReturn(true);
        EasyMock.replay(mockTrainingImageRepository);
        assertFalse(trainingImageService.deleteTrainingImage(ID));
        EasyMock.verify(mockTrainingImageRepository);
    }
}
