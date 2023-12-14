package com.diagnomind.web_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnomind.web_server.domain.image.model.Image;
import com.diagnomind.web_server.domain.training_data.service.TrainingDataService;
import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnomind")
public class UserController {
    
    private final TrainingDataService trainingDataService;

    @PostMapping(value = "/uploadImage", consumes = { "application/json", "application/xml" })
    public ResponseEntity<Image> uploadImage(@RequestBody TrainingImage img) {
        return (trainingDataService.addTrainImage(img)) ? new ResponseEntity<>(HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
