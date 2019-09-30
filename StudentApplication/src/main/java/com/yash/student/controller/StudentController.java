package com.yash.student.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.student.beans.Student;
import com.yash.student.exception.RecordNotFoundException;
import com.yash.student.services.IStudent;

@RestController
@RequestMapping("/students")
public class StudentController {

	Logger log = Logger.getLogger(StudentController.class.getName());

	@Autowired
	IStudent studentService;

	@PostMapping(value = "/add")
	public void postStudents(@RequestBody Student student) {
		studentService.save(student);

	}

	@GetMapping(value = "/read")
	public List<Student> getAllStudents() {
		return studentService.getStudents();
	}

	@GetMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> findById(@PathVariable Long id) {
		Optional<Student> stud = studentService.getStudentById(id);
		if (!stud.isPresent()) {
			throw new RecordNotFoundException("Invalid employee id : " + id);
		}
		return ResponseEntity.ok(stud.get());
	}

	@DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Student> delete(@PathVariable Long id) {

		if (!studentService.getStudentById(id).isPresent()) {

			throw new RecordNotFoundException("Student is not exist ");
		}
		studentService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { "application/json", "application/xml" })
	public Student updateEmpById(@PathVariable Long id, @RequestBody Student updateStud) {
		Optional<Student> student = studentService.getStudentById(id);

		if (!student.isPresent()) {
			throw new RecordNotFoundException("Id not exist");
		}

		if (updateStud.getName() == null || updateStud.getName().isEmpty())
			updateStud.setName(updateStud.getName());

		// updateStud.setId(id);
		return studentService.updateById(updateStud);

	}

}
