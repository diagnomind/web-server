package com.diagnomind.web_server.domain.training_image.model;

import com.diagnomind.web_server.domain.image.model.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "training_image")
public class TrainingImage {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "is_correct_diagnosis")
    private boolean isCorrectDiagnosis;

    @OneToOne(mappedBy = "trainingImage")
    private Image image;
}
