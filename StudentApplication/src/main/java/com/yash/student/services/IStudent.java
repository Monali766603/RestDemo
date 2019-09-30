package com.yash.student.services;

import java.util.List;
import java.util.Optional;

import com.yash.student.beans.Student;

public interface IStudent {

	public void deleteById(long id);

	public List<Student> getStudents();

	public Optional<Student> getStudentById(long id);
	 
	public Student save(Student student);
	public Student updateById(Student stud);

}
