package com.diagnomind.web_server.domain.image.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.image.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {

}
