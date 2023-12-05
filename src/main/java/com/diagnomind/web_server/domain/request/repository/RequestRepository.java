package com.diagnomind.web_server.domain.request.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.request.model.Request;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    
}
