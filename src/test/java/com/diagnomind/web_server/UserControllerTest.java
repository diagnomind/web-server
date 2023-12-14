package com.diagnomind.web_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diagnomind.web_server.controller.UserController;
import com.diagnomind.web_server.domain.image.model.Image;
import com.diagnomind.web_server.domain.training_data.model.TrainingData;
import com.diagnomind.web_server.domain.training_data.service.TrainingDataService;
import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

public class UserControllerTest extends EasyMockSupport {
    
    private static final Long ID = 1L;

    private TrainingDataService mockTrainingDataService;
    private UserController userController;
    private TrainingData trainingData;

    @BeforeEach
    void setup() {
        mockTrainingDataService = createStrictMock(TrainingDataService.class);
        userController = new UserController(mockTrainingDataService);
        trainingData = new TrainingData();
        trainingData.setId(ID);
    }

    @Test
    void uploadImageCorrectTest() {
        TrainingImage img = new TrainingImage();
        img.setTrainingData(trainingData);
        EasyMock.expect(mockTrainingDataService.addTrainImage(img)).andReturn(true);
        EasyMock.replay(mockTrainingDataService);
        ResponseEntity<Image> response = userController.uploadImage(img);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        EasyMock.verify(mockTrainingDataService);
    }

    @Test
    void uploadImageFailTest() {
        TrainingImage img = new TrainingImage();
        EasyMock.expect(mockTrainingDataService.addTrainImage(img)).andReturn(false);
        EasyMock.replay(mockTrainingDataService);
        ResponseEntity<Image> response = userController.uploadImage(img);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        EasyMock.verify(mockTrainingDataService);
    }

}
