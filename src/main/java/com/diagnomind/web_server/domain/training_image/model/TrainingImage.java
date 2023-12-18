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

/**
 * Represents an image entity with details such as image data, linked to a training image.
 */
@Getter
@Setter
@Entity
@Table(name = "training_image")
public class TrainingImage {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the image.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The binary data representing the image. Cannot be null.
     */
    @Column(nullable = false, name = "is_correct_diagnosis")
    private boolean isCorrectDiagnosis;

    /**
     * The associated training image for this image.
     */
    @OneToOne(mappedBy = "trainingImage")
    private Image image;
}
