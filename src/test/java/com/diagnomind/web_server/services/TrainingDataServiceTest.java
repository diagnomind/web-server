package com.diagnomind.web_server.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diagnomind.web_server.domain.training_data.model.TrainingData;
import com.diagnomind.web_server.domain.training_data.repository.TrainingDataRepository;
import com.diagnomind.web_server.domain.training_data.service.TrainingDataService;
import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

class TrainingDataServiceTest extends EasyMockSupport {
 
    private static final Long ID = 1L;

    TrainingData trainingData;
    TrainingDataRepository mockTrainingDataRepository;
    TrainingDataService trainingDataService;

    @BeforeEach
    void setup() {
        trainingData = new TrainingData();
        mockTrainingDataRepository = createStrictMock(TrainingDataRepository.class);
        trainingDataService = new TrainingDataService(mockTrainingDataRepository);
    }

    @Test
    void addTrainImageCorrectTest() {
        TrainingImage img = new TrainingImage();
        trainingData.setTrainingImages(new ArrayList<>());
        EasyMock.expect(mockTrainingDataRepository.findById(ID)).andReturn(Optional.of(trainingData));
        EasyMock.replay(mockTrainingDataRepository);
        trainingDataService.addTrainImage(img);
        assertTrue(trainingData.getTrainingImages().contains(img));
        EasyMock.verify(mockTrainingDataRepository);
    }

    @Test
    void addTrainImageFailTest() {
        TrainingImage img = new TrainingImage();
        trainingData.setTrainingImages(new ArrayList<>());
        EasyMock.expect(mockTrainingDataRepository.findById(ID)).andReturn(Optional.empty());
        EasyMock.replay(mockTrainingDataRepository);
        trainingDataService.addTrainImage(img);
        assertFalse(trainingData.getTrainingImages().contains(img));
        EasyMock.verify(mockTrainingDataRepository);
    }

    @Test
    void getTrainingImagesTest() {
        List<TrainingImage> imagesList = new ArrayList<>();
        trainingData.setTrainingImages(new ArrayList<>());
        trainingData.setTrainingImages(imagesList);
        EasyMock.expect(mockTrainingDataRepository.findById(ID)).andReturn(Optional.of(trainingData));
        EasyMock.replay(mockTrainingDataRepository);
        assertEquals(trainingDataService.getTrainingImages(), imagesList);
        EasyMock.verify(mockTrainingDataRepository);
    }

    @Test
    void deleteTrainingDataCorrectTest() {
        TrainingImage img = new TrainingImage();
        img.setId(ID);
        trainingData.setTrainingImages(new ArrayList<>());
        trainingData.getTrainingImages().add(img);
        EasyMock.expect(mockTrainingDataRepository.findById(ID)).andReturn(Optional.of(trainingData));
        EasyMock.replay(mockTrainingDataRepository);
        assertTrue(trainingDataService.deleteTrainingData(ID));
        EasyMock.verify(mockTrainingDataRepository);
    }

    @Test
    void deleteTrainingDataFailTest() {
        TrainingImage img = new TrainingImage();
        img.setId(ID + 1);
        trainingData.setTrainingImages(new ArrayList<>());
        trainingData.getTrainingImages().add(img);
        EasyMock.expect(mockTrainingDataRepository.findById(ID)).andReturn(Optional.of(trainingData));
        EasyMock.replay(mockTrainingDataRepository);
        assertFalse(trainingDataService.deleteTrainingData(ID));
        EasyMock.verify(mockTrainingDataRepository);
    }

}
