package com.diagnomind.web_server.domain.train_image.model;

import com.diagnomind.web_server.domain.image.model.Image;
import com.diagnomind.web_server.domain.training_data.model.TrainingData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class TrainImage {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean isCorrectDiagnosis;

    @OneToOne
    private Image image;

    @ManyToOne
    private TrainingData trainingData;
}
