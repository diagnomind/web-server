package com.diagnomind.web_server.domain.hospital.repository;

import org.springframework.data.repository.CrudRepository;

import com.diagnomind.web_server.domain.hospital.model.Hospital;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {
    
}
