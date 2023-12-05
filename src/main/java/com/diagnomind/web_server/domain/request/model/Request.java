package com.diagnomind.web_server.domain.request.model;

import java.sql.Date;

import com.diagnomind.web_server.domain.feedback.model.Feedback;
import com.diagnomind.web_server.domain.image.model.Image;
import com.diagnomind.web_server.domain.user.model.User;

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
public class Request {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    // @JoinColumn(nullable = true)
    private User user;

    @OneToOne
    private Image image;

    @OneToOne
    private Feedback feedback;
}
