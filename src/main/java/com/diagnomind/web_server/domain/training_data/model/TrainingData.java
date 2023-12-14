package com.diagnomind.web_server.domain.training_data.model;

import java.util.List;

import com.diagnomind.web_server.domain.training_image.model.TrainingImage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table
@Getter
public class TrainingData {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public enum Type {
        Cerebral,
        Pulmon,
    }

    @OneToMany(mappedBy = "id")
    private List<TrainingImage> trainingImages;
}
