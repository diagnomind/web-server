package com.diagnomind.web_server.domain.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.user.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
