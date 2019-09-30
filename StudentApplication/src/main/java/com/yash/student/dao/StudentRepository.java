package com.yash.student.dao;

import org.springframework.data.repository.CrudRepository;

import com.yash.student.beans.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
