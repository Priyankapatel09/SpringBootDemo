package com.springbootdemo.crud.restful.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootdemo.crud.restful.webservices.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student ,Long>{

}
