package com.diagnomind.web_server.domain.image.model;

import java.util.List;

import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents an image entity with details such as image data, linked to a training image.
 */
@Entity
@Table(name = "image")
public class Image {
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
    @Column(nullable = false, name = "image_data")
    private List<Byte> imageData;

    /**
     * The associated training image for this image.
     */
    @OneToOne
    private TrainingImage trainingImage;
}
