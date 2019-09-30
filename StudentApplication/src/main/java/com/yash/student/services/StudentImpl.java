package com.yash.student.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.student.beans.Student;
import com.yash.student.dao.StudentRepository;

@Service
public class StudentImpl implements IStudent {

	@Autowired
	StudentRepository studentRepository;

	public Student save(Student student) {

		return studentRepository.save(student);

		/*
		 * Student stud = new Student();
		 * stud.setFirstName(student.getFirstName());
		 * stud.setLastName(student.getLastName());
		 * stud.setGender(student.getGender());
		 * stud.setMobileNO(student.getMobileNO()); return stud;
		 */

	}

	public List<Student> getStudents() {
		List<Student> studentList = (List<Student>) studentRepository.findAll();
		System.out.println(studentList);
		return studentList;

	}

	public Optional<Student> getStudentById(long id) {
		return studentRepository.findById(id);
	}

	@Override
	public void deleteById(long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student updateById(Student stud) {
		return studentRepository.save(stud);

	}

}
