package com.pms.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.dio.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
