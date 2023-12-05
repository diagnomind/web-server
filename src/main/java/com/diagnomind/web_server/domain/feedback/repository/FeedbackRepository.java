package com.diagnomind.web_server.domain.feedback.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.feedback.model.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
    
}
